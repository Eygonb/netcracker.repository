package org.di.configuration;

public class JavaConfiguration implements Configuration {
    @Override
    public String[] getPackagesToScan() {
        return new String[]{"com.netcracker", "org.di"};
    }
}
