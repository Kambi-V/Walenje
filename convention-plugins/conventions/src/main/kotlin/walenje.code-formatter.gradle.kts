plugins {
  id("com.ncorti.ktfmt.gradle")
}

ktfmt {
  googleStyle()
  removeUnusedImports = true
  maxWidth = 100
  manageTrailingCommas = true
}

tasks.named("preBuild").configure { dependsOn("ktfmtFormat") }
