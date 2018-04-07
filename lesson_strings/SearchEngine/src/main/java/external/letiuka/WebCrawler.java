package external.letiuka;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    public final String URLregex = "(https?|ftp)://[^\\s/$.?#].[^\\s\"]*";
    HashMap<String, HashMap<String, Integer>> wordMap;
    Queue<String> URLnames;
    WebReader webReader;

    public WebCrawler(String EntryURL) {
        wordMap = new HashMap<String, HashMap<String, Integer>>();
        URLnames = new LinkedList<String>();
        URLnames.add(EntryURL);
        webReader = new WebReader();

    }

    public HashMap<String, Integer> findFreqFromURL(String page, int minLength, int minEntries)
            throws IOException {
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        String[] words = page.split("[^\\w]");
        for (String a : words) {
            a = a.toLowerCase();
            if (a.length() < minLength) continue;
            Integer freq;
            freq = res.get(a);
            res.put(a, (freq == null) ? 1 : freq + 1);
        }
        for (String a : words) {
            a = a.toLowerCase();
            if (a.length() < minLength) continue;
            Integer freq = res.get(a);
            if (freq != null && freq < minEntries)
                res.remove(a);
        }
        return res;
    }

    void mergeTree(HashMap<String, Integer> siteTree, String URLname) {
        for (Map.Entry<String, Integer> entry : siteTree.entrySet()) {
            String word = entry.getKey();
            Integer freq = entry.getValue();
            HashMap<String, Integer> URLmap = wordMap.get(word);
            if (URLmap == null) wordMap.put(word, URLmap = new HashMap<String, Integer>());
            URLmap.put(URLname, freq);
        }
    }

    public void addLinks(String str) {
        Pattern URLpat = Pattern.compile(URLregex);
        Matcher matcher = URLpat.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (!group.contains(".css") && !group.contains(".js"))
                URLnames.add(matcher.group());
        }
    }

    public void scan(int URLcount) {
        String URLname = URLnames.poll();
        if (URLcount-- == 0 || URLname == null) return;
        try {
            String str = webReader.readString(URLname);
            String strNoTags = str.replaceAll("<.*?>", " ");
            addLinks(str);
            mergeTree(findFreqFromURL(strNoTags, 3, 5), URLname);
            System.out.println("Reading from "+URLname+"...");
        } catch (IOException e) {
            System.err.println("Could not connect to " + URLname);
            ++URLcount;
        }
        scan(URLcount);
    }

    public void print() {
        Iterator it = wordMap.entrySet().iterator();
        if (!it.hasNext()) System.out.println("Nothing found");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (print((String) entry.getKey())) System.out.println();
        }
    }

    public boolean print(String word) {
        word = word.toLowerCase();
        HashMap<String, Integer> res = wordMap.get(word);
        if (res == null || res.entrySet().isEmpty()) {
            return false;
        }
        System.out.println("All occurences of " + word);
        Object[] reslist = res.entrySet().toArray();
        Arrays.sort(reslist, new Comparator() {
            public int compare(Object a, Object b) {
                return (Integer) ((Map.Entry) b).getValue() - (Integer) ((Map.Entry) a).getValue();
            }
        });
        for (Object obj : reslist) {
            Map.Entry pair = (Map.Entry) obj;
            System.out.println("Found " + word + " " + pair.getValue() + " times in " + pair.getKey());
        }

        return true;

    }


}
