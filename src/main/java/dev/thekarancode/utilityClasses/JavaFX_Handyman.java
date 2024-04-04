package dev.thekarancode.utilityClasses;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaFX_Handyman {
    private static final Set<Character> supportedCharacters = new HashSet<>();

    static {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            supportedCharacters.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            supportedCharacters.add(ch);
        }
        for (char ch = '0'; ch <= '9'; ch++) {
            supportedCharacters.add(ch);
        }
        supportedCharacters.add(' ');
        supportedCharacters.add('~');
        supportedCharacters.add('!');
        supportedCharacters.add('@');
        supportedCharacters.add('#');
        supportedCharacters.add('$');
        supportedCharacters.add('%');
        supportedCharacters.add('^');
        supportedCharacters.add('&');
        supportedCharacters.add('*');
        supportedCharacters.add('(');
        supportedCharacters.add(')');
        supportedCharacters.add('_');
        supportedCharacters.add('+');
        supportedCharacters.add('-');
        supportedCharacters.add('=');
        supportedCharacters.add('`');
        supportedCharacters.add('{');
        supportedCharacters.add('}');
        supportedCharacters.add('|');
        supportedCharacters.add(';');
        supportedCharacters.add(':');
        supportedCharacters.add('\'');
        supportedCharacters.add('/');
        supportedCharacters.add('?');
        supportedCharacters.add('<');
        supportedCharacters.add('>');
        supportedCharacters.add(',');
        supportedCharacters.add('.');
        supportedCharacters.add('[');
        supportedCharacters.add(']');
        supportedCharacters.add('\\');
        supportedCharacters.add('"');
    }

    public boolean hasUnsupportedChar(String inputString) {
        for (char ch : inputString.toCharArray()) {
            if (!supportedCharacters.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    public List<Node> getAllDescendentList(Parent parentNode) {
        List<Node> children = new ArrayList<>();
        for (Node child : parentNode.getChildrenUnmodifiable()) {
            children.add(child);
            if (child instanceof Parent) {
                children.addAll(getAllDescendentList((Parent) child));
            }
        }
        return children;
    }
}
