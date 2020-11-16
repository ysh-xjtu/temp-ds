public class BST {
    public BinNode root;

    public BST() {
        root = null;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void printhelp(BinNode rt, int level) {
        if (rt == null)
            return;
        printhelp(rt.left(), level + 1);
        System.out.print(rt.key() + " ");
        System.out.println(rt.element);
        printhelp(rt.right(), level + 1);
    }

    public void print() {
        if (root == null) {
            System.out.println("THE BST is empty");
        } else {
            printhelp(root, 0);
            System.out.println();
        }
    }

    public String find(String key) {
        return findhelp(root, key);
    }

    private String findhelp(BinNode rt, String key) {
        if (rt == null)
            return null;
        String it = rt.key();
        if (it.compareTo(key) > 0)
            return findhelp(rt.left(), key);
        else if (it.compareTo(key) == 0)
            return rt.element();
        else
            return findhelp(rt.right(), key);
    }

    public void insert(String key, String val) {
        root = inserthelp(root, key, val);
    }

    public BinNode inserthelp(BinNode rt, String key, String val) {
        if (null == rt)
            rt = new BinNode(key, val);
        String it = rt.key();
        if (it.compareTo(key) > 0) {
            rt.setleft(inserthelp(rt.left(), key, val));
        } else if (it.compareTo(key) < 0) {
            rt.setright(inserthelp(rt.right(), key, val));
        } else {
            rt.element = val;
        }
        return rt;
    }

    private String getminkey(BinNode rt) {
        if (rt.left() == null)
            return rt.key();
        else
            return getminkey(rt.left());
    }

    private String getminelement(BinNode rt) {
        if (rt.left() == null)
            return rt.element;
        else
            return getminelement(rt.left());
    }

    private BinNode deletemin(BinNode rt) {
        if (rt.left() == null)
            return rt.right();
        else {
            rt.setleft(deletemin(rt.left()));
            return rt;
        }
    }

    public String remove(String key, String val) {
        String str = find(key);
        root = removehelp(root, key, val);
        return str;
    }

    private BinNode removehelp(BinNode rt, String key, String val) {
        if (rt == null)
            return null;
        String it = rt.key();
        if (it.compareTo(key) > 0)
            rt.setleft(removehelp(rt.left(), key, val));
        else
        if (it.compareTo(key) < 0)
            rt.setright(removehelp(rt.right(), key, val));
        else {
            if (rt.left() == null)
                rt = rt.right();
            else if (rt.right() == null)
                rt = rt.left();
            else {
                rt.setkey(getminkey(rt.right()));
                rt.setelement(getminelement(rt.right()));
                rt.setright(deletemin(rt.right()));
            }
        }
        return rt;
    }

    public String find1(String key, String value) {
        return findhelp1(root, key, value);
    }

    public boolean update(String key, String value) {
        String str = find1(key, value);
        if (str == null)
            return false;
        else
            return true;
    }

    private String findhelp1(BinNode rt, String key, String value) {
        if (rt == null)
            return null;
        String it = rt.key();
        if (it.compareTo(key) > 0)
            return findhelp1(rt.left(), key, value);
        else if (it.compareTo(key) == 0) {
            rt.element = value;
            return rt.key();
        } else
            return findhelp1(rt.right(), key, value);
    }
    public static void main(String[] args){
        BST tree = new BST();
        tree.insert("one","one");
        tree.insert("two", "two");
        tree.insert("apple", "apple");
        tree.remove("apple","apple");
        tree.print();
    }
}