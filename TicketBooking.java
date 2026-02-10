class TicketCounter {
    private int availableTickets = 10;

    // synchronized method (critical section)
    public synchronized void bookTicket(String user, int ticketsRequested) {
        System.out.println(user + " is trying to book " + ticketsRequested + " tickets");

        if (ticketsRequested <= availableTickets) {
            System.out.println("Booking successful for " + user);
            availableTickets -= ticketsRequested;
            System.out.println("Remaining tickets: " + availableTickets);
        } else {
            System.out.println("Booking failed for " + user + " (Not enough tickets)");
        }

        System.out.println("----------------------------------");
    }
}

class User extends Thread {
    private TicketCounter counter;
    private String userName;
    private int tickets;

    User(TicketCounter counter, String userName, int tickets) {
        this.counter = counter;
        this.userName = userName;
        this.tickets = tickets;
    }

    public void run() {
        counter.bookTicket(userName, tickets);
    }
}

public class TicketBooking {
    public static void main(String[] args) {

        TicketCounter counter = new TicketCounter();

        User u1 = new User(counter, "User-1", 4);
        User u2 = new User(counter, "User-2", 5);
        User u3 = new User(counter, "User-3", 3);

        u1.start();
        u2.start();
        u3.start();
    }
}
