package com.sundayliu.gameparser;

import de.robv.android.xposed.IXposedHookLoadPackage;
//import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

//import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class GameParser implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        //XposedBridge.log("Loaded app1 : " + lpparam.packageName);
        String pkgName = lpparam.packageName;
        if (!(
                (pkgName.equals("com.tencent.tmgp.speedmobile"))
                        //|| (pkgName.equals("com.tencent.tmgp.sgame"))
                        || (pkgName.startsWith("com.tencent.gamestick"))
                        || (pkgName.startsWith("io.virtualapp"))
        )
                )
        {
            //XposedBridge.log("Loaded app1 : " + lpparam.packageName);
            return;
        }

        XposedBridge.log("hooked app: " + lpparam.packageName);

    }

    private void printStack() {
        XposedBridge.log("Printing stack trace:");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        long id = Thread.currentThread().getId();
        for (int i = 1; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            XposedBridge.log("\tat[" + id + "]" + s.getClassName() + "." + s.getMethodName()
                    + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
        }
    }
}
