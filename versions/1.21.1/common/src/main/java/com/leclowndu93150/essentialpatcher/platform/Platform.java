package com.leclowndu93150.essentialpatcher.platform;

import java.nio.file.Path;

public interface Platform {

    Path getConfigDir();

    Path getGameDir();

    final class Holder {
        private static Platform instance;

        public static void set(Platform platform) {
            instance = platform;
        }

        public static Platform get() {
            return instance;
        }
    }
}
