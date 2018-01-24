package sandbox;

import com.dimbo.helper.Localization;

import java.io.IOException;

public class BundleTest {
    public static void main(String[] args) throws IOException {
        
        System.out.println(Localization.getBundleString("ru"));
        System.out.println(Localization.getBundleString("en"));
    }
}
