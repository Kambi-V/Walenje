plugins { id("com.ncorti.ktfmt.gradle") }

ktfmt {
  googleStyle()
  removeUnusedImports = true
  maxWidth = 100
}

tasks.named("preBuild").configure {
  dependsOn("ktfmtFormatScripts")
  dependsOn("ktfmtFormat")
}
