/*************************************
 * Filename:  Sender.java
 *************************************/
import java.util.Random;

public class Sender extends NetworkHost


//I did try to implement the project with packetLoss and checksum, however after mutiple attempts to
//try and get it working correctly, i couldnt do it. please have a look at my commentde out methods 
// in the output and in the Input of both receiver and Sender.
//when running my code with the commented out lines, only one essage would succefully send. please couldn
//you have a look ad tell me where i was going wrong. thanks.
{
    /*
     * Predefined Constant (static member variables):
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
    // state information for the sender.
   
    
    int astate, bstate = 0;
    Packet astored_pkt, bstored_pkt;
    
    
    //Creating the checksumming method.
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

    
    
    
    
    
    

    // Also add any necessary methods (e.g. for checksumming)
    
    // This is the constructor. Don't touch!!!
    public Sender(int entityName,
                       EventList events,
                       double pLoss,
                       double pCorrupt,
                       int trace,
                       Random random)
    {
        super(entityName, events, pLoss, pCorrupt, trace, random);
    }

    // This routine will be called whenever the app layer at the sender
    // has a message to send.  The job of your protocol is to insure that
    // the data in such a message is delivered in-order, and correctly, to
    // the receiving application layer.
    protected void Output(Message message)
    {
        
    astored_pkt = new Packet(0,0,0, message.getData());
    udtSend(astored_pkt);
        
 /*       System.out.println(message);
        if (astate == 0){
            int checksum = calculateChecksum(message.getData(),0,0);
            astored_pkt = new Packet(0,0,checksum, message.getData());
            udtSend(astored_pkt);
            TimerInterrupt();
            astate = 1;
            
        }else if (astate == 2){
            int checksum = calculateChecksum(message.getData(),1,1);
            astored_pkt = new Packet(1,1,checksum, message.getData());
            TimerInterrupt();
            astate = 3;
        }
        else
                System.out.println("timeout");
*/
    }
    
    // This routine will be called whenever a packet sent from the receiver 
    // (i.e. as a result of a udtSend() being done by a receiver procedure)
    // arrives at the sender.  "packet" is the (possibly corrupted) packet
    // sent from the receiver.
    protected void Input(Packet packet)
    {
        
       
/*
        if(packet.getAcknum() == 0)
            System.out.println("a : got ACK0, waiting for ACK1");
        if (packet.getAcknum() == 1){
            stopTimer(0);
            astate = 0;
            System.out.print("A got ACK 1")
        }
        else{}
*/
    }
    
    // This routine will be called when the senders's timer expires (thus 
    // generating a timer interrupt). You'll probably want to use this routine 
    // to control the retransmission of packets. See startTimer() and 
    // stopTimer(), above, for how the timer is started and stopped. 
    protected void TimerInterrupt()
    {
/*
        udtSend(astored_pkt);
        startTimer(40);
        stopTimer();
*/
   
    }
    
    // This routine will be called once, before any of your other sender-side 
    // routines are called. It can be used to do any required
    // initialization (e.g. of member variables you add to control the state
    // of the sender).
    protected void Init()
    {
        int seq = 0;

    }

}
