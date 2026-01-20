#!/bin/sh
rm -rf .idea
./gradlew clean
rm -rf .gradle
rm -rf build
rm -rf */build
rm -rf app/ios/Walenje.xcworkspace
rm -rf app/ios/Pods
rm -rf app/ios/Walenje.xcodeproj/project.xcworkspace
rm -rf app/ios/Walenje.xcodeproj/xcuserdata