class Encryptor {
    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = Constants.ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift) % Constants.ALPHABET.length();
                encrypted.append(Constants.ALPHABET.charAt(newIndex));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }
}
