public class BinNode {
    private String key;
    public String element;
    private BinNode left;
    private BinNode right;
    public BinNode() {
        left=right=null;
    }
    public BinNode(String key,String val){
        this.key=key;
        this.element=val;
        left=right=null;
    }
    public BinNode(String key,String val,BinNode left,BinNode right){
        this.key=key;
        element=val;
        this.left=left;
        this.right=right;
    }
    //return and set the element value
    public String element(){
        return element;
    }
    public String key(){
        return key;
    }
    public String setelement(String element){
        return this.element=element;
    }
    public String setkey(String key){
        return this.key=key;
    }
    public BinNode left(){
        return this.left;
    }
    public BinNode setleft(BinNode left){
        return this.left=left;
    }
    public void setleftelement(String element){
        this.left.element=element;
    }
    public void setrightelement(String element){
        this.right.element=element;
    }
    public BinNode right(){
        return this.right;
    }
    public BinNode setright(BinNode right){
        return this.right=right;
    }
    public boolean isleaf(){
        return(left==null)&&(right==null);
    }
}