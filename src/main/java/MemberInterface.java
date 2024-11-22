interface MemberInterface {
    public String getId();
    public String getInfo();
    public boolean borrowItem(LibraryItem item);
    public void returnItem(LibraryItem item);
}
