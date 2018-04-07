package external.letiuka.logic;


import external.letiuka.input.BookInput;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class BookParserTest {
    BookInput input = mock(BookInput.class);
    BookParser parser = new BookParser(input);

    @Test
    public void testPrepareString() {
        String a = parser.prepareString("bam<><>    sd <dsfs >");
        assertEquals("^bam sd$",a);
        a = parser.prepareString("bam<>< \"\">  \t  sd <dsfs > srt-");
        assertEquals("^bam sd srt$H",a);

    }
}
