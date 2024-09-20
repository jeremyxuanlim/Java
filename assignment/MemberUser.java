import java.io.FileWriter;
import java.io.IOException;

public class MemberUser extends User {

    public MemberUser(String name, String email, String password, double currency) {
        super(name, email, password, currency);
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("MemberUser.txt", true)) {
            writer.write(name + "," + email + "," + password + "," + currency + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
