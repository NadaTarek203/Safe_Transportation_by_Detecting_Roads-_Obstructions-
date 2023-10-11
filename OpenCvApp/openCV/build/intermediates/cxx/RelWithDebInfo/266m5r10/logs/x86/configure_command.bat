@echo off
"C:\\Users\\NADA\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\cmake.exe" ^
  "-HD:\\fcis\\Last Year\\Graduation Project\\OpenCvApp\\openCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=24" ^
  "-DANDROID_PLATFORM=android-24" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\NADA\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\NADA\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\NADA\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\NADA\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\fcis\\Last Year\\Graduation Project\\OpenCvApp\\openCV\\build\\intermediates\\cxx\\RelWithDebInfo\\266m5r10\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\fcis\\Last Year\\Graduation Project\\OpenCvApp\\openCV\\build\\intermediates\\cxx\\RelWithDebInfo\\266m5r10\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=RelWithDebInfo" ^
  "-BD:\\fcis\\Last Year\\Graduation Project\\OpenCvApp\\openCV\\.cxx\\RelWithDebInfo\\266m5r10\\x86" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
