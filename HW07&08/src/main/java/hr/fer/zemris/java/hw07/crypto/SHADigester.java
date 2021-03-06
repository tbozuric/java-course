package hr.fer.zemris.java.hw07.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class enables computing sha-256 file digest.
 */
public class SHADigester implements Digester {
    /**
     * Buffer size.
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * Message digest.
     */
    private MessageDigest sha;

    /**
     * Creates an instance of {@link SHADigester}.
     */
    public SHADigester() {
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new ShaDigestException("An error occurred while creating a message digest instance");
        }
    }

    /**
     * Calculates the SHA-256  file digest.
     *
     * @param file whose SHA-256 digest we want to calculate.
     * @return digest of the given file as a byte array.
     * @throws IOException if it is not possible to open the file.
     */
    @Override
    public byte[] digest(Path file) throws IOException {
        InputStream is = Files.newInputStream(file, StandardOpenOption.READ);
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while ((read = is.read(buffer)) != -1) {
            sha.update(buffer, 0, read);
        }
        return sha.digest();
    }
}
