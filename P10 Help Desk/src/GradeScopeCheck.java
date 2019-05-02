
public class GradeScopeCheck {

  public static void main(String[] args) {
    HelpDesk hd = new HelpDesk(4);  
    hd.createNewTicket("1");
    hd.createNewTicket("2");
    hd.createNewTicket("3");
    System.out.println(hd.closeNextTicket());  
    System.out.println(hd.closeNextTicket());
    System.out.println(hd.closeNextTicket());
    
    HelpDesk hd2 = new HelpDesk(4); 
    hd2.createNewTicket("12");
    hd2.createNewTicket("1234");
    hd2.createNewTicket("11");
    hd2.createNewTicket("123456");
    System.out.println(hd2.closeNextTicket());
    hd2.createNewTicket("123");
    System.out.println(hd2.closeNextTicket());
    System.out.println(hd2.closeNextTicket());
    System.out.println(hd2.closeNextTicket());
    System.out.println(hd2.closeNextTicket());
    System.out.println(hd2.closeNextTicket());
  }

}
