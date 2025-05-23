plugins { id("com.ncorti.ktfmt.gradle") }

ktfmt {
  googleStyle()
  removeUnusedImports = true
  maxWidth = 100
}

tasks.named("build").configure {
  dependsOn("ktfmtFormatScripts")
  dependsOn("ktfmtFormat")
}
