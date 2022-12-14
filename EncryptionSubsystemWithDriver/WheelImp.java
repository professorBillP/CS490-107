import EncryptorSubsystem.EncryptorFacade;
import P2PInterfacePackage.*;
import java.util.ArrayList;

public class WheelImp
{

   public static void main(String[] args)  
   {
      final int    wS =    95;

      ArrayList<Integer> iList  = new 
                                    ArrayList<>();
      
      AuditInterfaceFactory aIf = 
                      new AuditInterfaceFactory();
      
      AuditInterfaceStubBase aInt = aIf.create(1);
 
      EncryptorFacade eFac = 
                       EncryptorFacade.Instance();
      
      for (int i=0; i<500; ++i)
      {
         final int t1 = eFac.encrypt(i%wS);
         iList.add(t1);
      }
      
      aInt.SendEOK("Teamx", "good", true);
      
      
      int kLast = 0;
      for (int i=499; i>=0; --i)
      {
         int t1 = iList.get(i);

         int k  = eFac.decrypt(t1);
         
         if ((( k - kLast ) > 1 )&&( kLast != 0 ))
         {
            System.out.println ("ERROR ********");
            System.exit(k);
         }
         kLast = k;
         System.out.println(k);
      }

      aInt.SendDOK("Teamx", "good", true);

   }

}
