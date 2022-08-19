// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
apply(from = "../contrib-configuration/common.gradle.kts")

plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.intellij")
}

intellij {
  pluginName.set("Yeoman")
  plugins.set(listOf("JavaScript"))

  version.set("LATEST-EAP-SNAPSHOT")
  type.set("IU")
}

sourceSets {
  main {
    java {
      setSrcDirs(listOf("src"))
    }
    resources {
      setSrcDirs(listOf("resources", "compatibilityResources"))
    }
  }
  test {
    java {
      setSrcDirs(listOf("testSrc"))
    }
  }
}

tasks {
  prepareSandbox {
    from("resources") {
      include("runner/**/*.js")
      into("Yeoman")
    }
  }
}