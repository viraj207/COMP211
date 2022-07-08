/*************************************
 * Filename:  Receiver.java
 *************************************/
import java.util.Random;

public class Receiver extends NetworkHost

{
    /*
     * Predefined Constants (static member variables):
     *
     *   int MAXDATASIZE : the maximum size of the Message data and
     *                     Packet payload
     *
     *
     * Predefined Member Methods:
     *
     *  void startTimer(double increment):
     *       Starts a timer, which will expire in
     *       "increment" time units, causing the interrupt handler to be
     *       called.  You should only call this in the Sender class.
     *  void stopTimer():
     *       Stops the timer. You should only call this in the Sender class.
     *  void udtSend(Packet p)
     *       Puts the packet "p" into the network to arrive at other host
     *  void deliverData(String dataSent)
     *       Passes "dataSent" up to app layer. You should only call this in the
     *       Receiver class.
     *  double getTime()
     *       Returns the current time in the simulator.  Might be useful for
     *       debugging.
     *  void printEventList()
     *       Prints the current event list to stdout.  Might be useful for
     *       debugging, but probably not.
     *
     *
     *  Predefined Classes:
     *
     *  Message: Used to encapsulate a message coming from app layer
     *    Constructor:
     *      Message(String inputData): 
     *          creates a new Message containing "inputData"
     *    Methods:
     *      boolean setData(String inputData):
     *          sets an existing Message's data to "inputData"
     *          returns true on success, false otherwise
     *      String getData():
     *          returns the data contained in the message
     *  Packet: Used to encapsulate a packet
     *    Constructors:
     *      Packet (Packet p):
     *          creates a new Packet, which is a copy of "p"
     *      Packet (int seq, int ack, int check, String newPayload)
     *          creates a new Packet with a sequence field of "seq", an
     *          ack field of "ack", a checksum field of "check", and a
     *          payload of "newPayload"
     *      Packet (int seq, int ack, int check)
     *          chreate a new Packet with a sequence field of "seq", an
     *          ack field of "ack", a checksum field of "check", and
     *          an empty payload
     *    Methods:
     *      boolean setSeqnum(int n)
     *          sets the Packet's sequence field to "n"
     *          returns true on success, false otherwise
     *      boolean setAcknum(int n)
     *          sets the Packet's ack field to "n"
     *          returns true on success, false otherwise
     *      boolean setChecksum(int n)
     *          sets the Packet's checksum to "n"
     *          returns true on success, false otherwise
     *      boolean setPayload(String newPayload)
     *          sets the Packet's payload to "newPayload"
     *          returns true on success, false otherwise
     *      int getSeqnum()
     *          returns the contents of the Packet's sequence field
     *      int getAcknum()
     *          returns the contents of the Packet's ack field
     *      int getChecksum()
     *          returns the checksum of the Packet
     *      String getPayload()
     *          returns the Packet's payload
     *
     */

    // Add any necessary class variables here. They can hold
    // state information for the receiver.
    
        
    int astate, bstate = 0;
    Packet astored_pkt, bstored_pkt;
    
    
    // Also add any necessary methods (e.g. for checksumming)
    //CheckSumming method
        private int calculateChecksum(String payload, int seqnum, int acknum){
        int characterValue;
        int i = 0;
        
        int maximum = payload.length();
        
        for (int x = 0; x < maximum; x++){
            characterValue = payload.charAt(x);
            i += characterValue;
        }
        
        i += seqnum;
        i += acknum;
        
        return i;
        
    }
   
    // This is the constructor. Don't touch!!!
    public Receiver(int entityName,
                       EventList events,
                       double pLoss,
                       double pCorrupt,
                       int trace,
                       Random random)
    {
        super(entityName, events, pLoss, pCorrupt, trace, random);
    }
        

    
    // This routine will be called whenever a packet sent from the sender
    // (i.e. as a result of a udtSend() being done by a Sender procedure)
    // arrives at the receiver.  "packet" is the (possibly corrupted) packet
    // sent from the sender.
    protected void Input(Packet packet){
 /*           if (bstate == 0){
            int checksum = calculateChecksum(message.getData(),0,0);
            bstored_pkt = new Packet(packet);
            dataSent(stored_pkt);
            startTimer(40);
            bstate = 1;
            
        }else if (bstate == 2){
            int checksum = calculateChecksum(message.getData(),1,1);
            bstored_pkt = new Packet(packet);
            startTimer(40);
            bstate = 3;
        
 */       
 
       String packetData = packet.getPayload();
      
      //everything here is acknowledgement and checksumming
      //Packet acknowlegdement = new Packet(0,0,0, "");
      Packet bstored_pkt;
      bstored_pkt = new Packet(0,0,0, "ACK!!!");
      udtSend(bstored_pkt);
      
      //end goal
      deliverData(packetData);
    }

   
    

    
    // This routine will be called once, before any of your other receiver-side
    // routines are called. It can be used to do any required
    // initialization (e.g. of member variables you add to control the state
    // of the receiver).
    protected void Init()
    {
    }

}
