public class TYIterator extends OHIterator {
    public TYIterator(OHRequest queue) {
        super(queue);
    }
    public OHRequest next() {
        OHRequest ans = super.next();
        if(hasNext() && ans.description.contains("thank u")){
            super.next();
        }
        return ans;
    }
}
