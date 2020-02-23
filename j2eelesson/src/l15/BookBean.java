package l15;

public class BookBean {
	private String name;
    private String author;
    private String publisher;
    private float price;
    private int quantity;     
    public String getName(){return name;}
    public String getAuthor(){return author;}
    public String getPublisher(){return publisher;}
    public float getPrice(){return price;}
    public int getQuantity(){return quantity;}	
    public void setName(String name) {   
        try{
            byte b[]=name.getBytes("ISO-8859-1");
	        this.name=new String(b);
        }catch(Exception e){}
     }
public void setAuthor(String author){
	  try{
            byte b[]=author.getBytes("ISO-8859-1");
            this.author=new String(b);
	  }catch(Exception e){}
    }
public void setPublisher(String publisher){
	  try{
      byte b[]=publisher.getBytes("ISO-8859-1");
      this.publisher=new String(b);
	  }catch(Exception e){}
   }
   public void setPrice(float price){this.price=price;}
   public void setQuantity(int quantity) {this.quantity=quantity;}
}


