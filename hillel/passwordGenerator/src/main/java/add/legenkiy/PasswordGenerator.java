package add.legenkiy;

public class PasswordGenerator {
    public String generatePassword(int length){
        String main = "1234567890qwertyuiopasdfghjklzxcvbnmWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * main.length());
            stringBuilder.append(main.charAt(index));
        }
        return stringBuilder.toString();
    }
}
