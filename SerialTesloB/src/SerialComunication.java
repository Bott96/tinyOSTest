
import java.io.IOException;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.PrintStreamMessenger;

public class SerialComunication implements MessageListener {
	PhoenixSource ps = BuildSource.makePhoenix("serial@/dev/ttyUSB0:telosb", PrintStreamMessenger.out);
	MoteIF mote = new MoteIF(ps);

	public SerialComunication() {
		mote.registerListener(new MioMex(), this);
		
		
	}

	
	@Override
	public void messageReceived(int arg0, Message message) {
		
		System.out.println("DA CHI  "+ ((MioMex) message).getNodeId()+ "   counter"+ ((MioMex) message).getCounter());
	}
	
	private void send() {
		int counter =0;
		
		MioMex m = new MioMex();
		
		
		//while (true) {
			//System.out.println("Sending packet " + counter);
			
			try {
				MioMex mex = new MioMex(32);
				mex.setCounter(0, 16, 1);
				mex.setCounter(16, 16, 11);
				System.out.println("INVIO");
				//moteID, MEX
				mote.send(1, mex);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
		

	}
	
	
	
	public static void main(String[] args) {
		SerialComunication SC = new SerialComunication();
		SC.send();
	
	}

}
