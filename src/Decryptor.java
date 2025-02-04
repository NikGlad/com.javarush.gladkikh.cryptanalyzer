class Decryptor {
    public static String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = Constants.ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index - shift + Constants.ALPHABET.length()) % Constants.ALPHABET.length();
                decrypted.append(Constants.ALPHABET.charAt(newIndex));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}

