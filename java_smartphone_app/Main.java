public class Main{
    public static void main(String[] args){
        while(true){
            System.out.println("smartphone app started !");
            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                // handle the exception
            }
        }
    }
}