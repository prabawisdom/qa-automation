package com.zillion.qa.config;

import com.zillion.qa.utils.Platform;
import com.zillion.qa.utils.Utils;

/**
 * Created by cmosher on 2017-02-07.
 */
public class DriverPathConfig {
    public static void setDriverPaths() {
        if (Utils.isGrid()) {
            return;
        }

        final String osName = Platform.OS.toUpperCase();

        if (osName.contains("WINDOWS")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_WINDOWS);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_WINDOWS);
            setPath(ApplicationConstants.IE_DRIVER_NAME, ApplicationConstants.IE_DRIVER_PATH);
        } else if (osName.contains("LINUX")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_LINUX);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_LINUX);
        }
        else if (osName.contains("MAC")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_MAC);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_MAC_OS);
            setPath(ApplicationConstants.SAFARI_DRIVER_NAME, ApplicationConstants.SAFARI_DRIVER_PATH);
        }
    }
    private static void setPath(final String propertyKey, final String driverPath) {
        System.setProperty(propertyKey, driverPath);
    }
}
