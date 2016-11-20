package com.example.pete.myapplication;

/**
 *
 *  FINGERPRINT SCANNING CODE:
 *
 *  Most of the code for fingerprint scanning came from a tutorial (see below). We made
 *  minor modifications to it to work the way we wanted with our application.
 *
 *  Credit for most of code in this class pertaining to fingerprint scanning and
 *  the explanation of how to use it goes to the tutorial at:
 *  http://www.techotopia.com/index.php/An_Android_Fingerprint_Authentication_Tutorial
 *
 *  Based on the book:
 *
 *  Android Studio 2.2
 *  Development Essentials
 *  Android 7 Edition
 *  eBook
 *
 *
 *
 **/

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.InvalidAlgorithmParameterException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;

import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// For working with database
import android.util.Log;
import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class ScanFinger extends AppCompatActivity {


    private static final String KEY_NAME = "example_key";
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button logoutButton;
    private Socket sock = null;

    // Connection successful for testing with url: rlwise.ddns.net
    // and port 8081
    private static final int SERVER_PORT = 8081;
    private static final String SERVER_IP = "jmullen.ddns.net";


    private static ScanFinger parent = new ScanFinger();

    public void logout() {
        logoutButton = (Button) findViewById(R.id.logout_tap);
        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                closeConnection();
                Intent page = new Intent(ScanFinger.this, MainActivity.class);
                startActivity(page);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanfinger);

        setParent(parent);
        logout();
        new Thread(new Client()).start();
        String receivedInfo = "info received from server here";

        if (sock != null){

            try {
                DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            } catch (Exception e) {
                e.printStackTrace();
                displayConnectionError();
            }
        }
    }
/*
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);


        if (!keyguardManager.isKeyguardSecure()) {

            Toast.makeText(this,
                    "Lock screen security not enabled in Settings",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,
                    "Fingerprint authentication permission not enabled",
                    Toast.LENGTH_LONG).show();

            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {

            // This happens when no fingerprints are registered.
            Toast.makeText(this,
                    "Register at least one fingerprint in Settings",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {

            // This happens when no fingerprints are registered.
            Toast.makeText(this,
                    "Register at least one fingerprint in Settings",
                    Toast.LENGTH_LONG).show();
            return;
        }

        generateKey();

        if (cipherInit()) {
            cryptoObject =
                    new FingerprintManager.CryptoObject(cipher);
        }

        if (cipherInit()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }


    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    */

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *//*
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ScanFinger Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/
    // End fingerprint scanning code

    public void setParent(ScanFinger parent) {
        this.parent = parent;
    }

    public void displayConnectionError() {
        parent.runOnUiThread(new Runnable() {
            public void run() {
                CharSequence text = "Could not start or unlock" +
                        " vehicle. Unable to make connection to vehicle" +
                        "over IP address/URL " + SERVER_IP + " and port " +
                        SERVER_PORT;;
                Context context = getApplicationContext();
                System.out.println(text);
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void displayConnectionSuccess() {
        parent.runOnUiThread(new Runnable() {
            public void run() {
                CharSequence text = "Successfully connected to vehicle on "
                        + " IP address/URL " + SERVER_IP + " and port " +
                        SERVER_PORT;
                Context context = getApplicationContext();
                System.out.println(text);
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void closeConnection() {
        try {
            if(sock !=null){
                sock.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Client implements Runnable {
        @Override
        public void run() {

            try {
                InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
                sock = new Socket(serverAddress, SERVER_PORT);
                displayConnectionSuccess();


            } catch (UnknownHostException e) {
                e.printStackTrace();
                displayConnectionError();

            } catch (IOException e) {
                e.printStackTrace();
                displayConnectionError();

            } catch(Exception e) {
                e.printStackTrace();
                displayConnectionError();
            }
        }
    }
}
