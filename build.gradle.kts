plugins {
    id("dev.prism")
}

group = "com.leclowndu93150"
version = "1.0.0"

prism {
    curseMaven()

    sharedCommon {
        mixin()
        mixinExtras()
        dependencies {
            localJar("libs/essential.jar")
        }
    }

    metadata {
        modId = "essentialpatcher"
        name = "Essential Patcher"
        description = "Debloats and patches the Essential mod. Unlock cosmetics client-side, disable telemetry, remove ads, and more."
        license = "MIT"
    }

    version("1.20.1") {
        common {
            compileOnly("curse.maven:yacl-667299:6336646")
        }
        fabric {
            loaderVersion = "0.18.6"
            fabricApi("0.92.7+1.20.1")
            dependencies {
                implementation("curse.maven:yacl-667299:6336639")
            }
        }
        forge {
            loaderVersion = "47.1.106"
            loaderVersionRange = "[47,)"
            dependencies {
                implementation("curse.maven:yacl-667299:6336646")
            }
        }
    }

    version("1.21.1") {
        common {
            compileOnly("curse.maven:yacl-667299:7437845")
        }
        fabric {
            loaderVersion = "0.18.6"
            fabricApi("0.116.10+1.21.1")
            dependencies {
                implementation("curse.maven:yacl-667299:7437845")
            }
        }
        neoforge {
            loaderVersion = "21.1.223"
            loaderVersionRange = "[4,)"
            dependencies {
                implementation("curse.maven:yacl-667299:7437845")
            }
        }
    }
}
