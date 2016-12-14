package hashtagrandom.games.seppe.stakescape.util;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import hashtagrandom.games.seppe.stakescape.gui.DuelArena;
import hashtagrandom.games.seppe.stakescape.pojo.User;

/**
 * Created by Thomas Machiels on 26-11-2016.
 */

public class XmlReader {

    private static final String ns = null;

    public XmlReader(){}

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<User> entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, DuelArena.rootElement);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("user")) {
                entries.add(readEntry(parser));
                System.out.println("data uit db " + "tag gevonden" + "---------------------------------------------------------" );
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private User readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, User.userElement);
        int win = 0;
        int loss = 0;
        int money = 0;
        int stake = 0;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("win")) {
                parser.require(XmlPullParser.START_TAG, ns, User.winElement);
                win = Integer.valueOf(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, User.winElement);
            } else if (name.equals("loss")) {
                parser.require(XmlPullParser.START_TAG, ns, User.lossElement);
                loss = Integer.valueOf(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, User.lossElement);
            } else if (name.equals("money")) {
                parser.require(XmlPullParser.START_TAG, ns, User.moneyElement);
                money = Integer.valueOf(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, User.moneyElement);
            } else if (name.equals("stake")) {
                parser.require(XmlPullParser.START_TAG, ns, User.stakeElement);
                stake = Integer.valueOf(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, User.stakeElement);
            } else {
                skip(parser);
            }
        }
        System.out.println("data uit db w" + win + "---------------------------------------------------------" );
        System.out.println("data uit db l" + loss + "---------------------------------------------------------" );
        System.out.println("data uit db m" + money + "---------------------------------------------------------" );
        System.out.println("data uit db s" + stake + "---------------------------------------------------------" );
        return new User(5,money,stake,win,loss);
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
