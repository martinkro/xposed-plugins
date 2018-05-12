package testxposed.zeewinpeng.com.testxposed;

import android.content.Intent;
import android.os.Bundle;

import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class TestXposed implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
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

        /*
        findAndHookMethod("tcs.aos", lpparam.classLoader, "dg", java.lang.String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("beforeHookedMethod");
                //printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("afterHookedMethod");
                //printStack();
            }
        });

        findAndHookMethod("tcs.rk", lpparam.classLoader, "p", android.content.Context.class, java.lang.String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("beforeHookedMethod");
                //printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("afterHookedMethod");
                //printStack();
            }
        });
        */

        /*
        findAndHookMethod("java.lang.StringBuilder", lpparam.classLoader, "append", java.lang.String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("beforeHookedMethod");
                printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("afterHookedMethod");
                printStack();
            }
        });
        */

        /*
        findAndHookMethod("java.lang.Runtime", lpparam.classLoader, "exec", java.lang.String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
               // XposedBridge.log("Runtime beforeHookedMethod " + param.args[0].toString());
                //printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Runtime afterHookedMethod");
                //printStack();
            }
        });

        */

        // Activity HOOK

        /*
        findAndHookMethod("android.app.Activity",lpparam.classLoader,"startActivity",android.content.Intent.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("startActivity1 beforeHookedMethod " + param.args[0].toString());
                printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("startActivity1 afterHookedMethod");
                //printStack();
            }
        });

        findAndHookMethod("android.app.Activity",lpparam.classLoader,"startActivity",android.content.Intent.class, android.os.Bundle.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("startActivity2 beforeHookedMethod " + param.args[0].toString());
                printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("startActivity2 afterHookedMethod");
                //printStack();
            }
        });
        */
        findAndHookMethod(
                "android.app.Activity",
                lpparam.classLoader,
                "startActivityForResult",
                android.content.Intent.class,
                int.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]startActivityForResult " + param.args[0].toString());
                        if (param.args[0] instanceof Intent){
                            Intent intent = (Intent)param.args[0];
                            printIntent(intent);
                        }
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]startActivityForResult");
                        //printStack();
                    }
        });


        findAndHookMethod(
                "android.content.Intent",
                lpparam.classLoader,
                "setDataAndType",
                android.net.Uri.class,
                java.lang.String.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]setDataAndType " + param.args[0].toString()  + "|" + param.args[1].toString());
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]setDataAndType");
                        //printStack();
                    }
        });

        // ContextImpl.java

        /*
        findAndHookMethod("android.app.ContextImpl",lpparam.classLoader,"startActivity",android.content.Intent.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("ContextImple::startActivity1 beforeHookedMethod " + param.args[0].toString());
                printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("ContextImple::startActivity1 afterHookedMethod");
                //printStack();
            }
        });

        findAndHookMethod("android.app.ContextImpl",lpparam.classLoader,"startActivity",android.content.Intent.class, android.os.Bundle.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("ContextImple::startActivity2 beforeHookedMethod " + param.args[0].toString());
                printStack();
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("ContextImple::startActivity2 afterHookedMethod");
                //printStack();
            }
        });
        */

        // android.app.Instrumentation
        /*
        public ActivityResult execStartActivity(
                Context who,
                IBinder contextThread,
                IBinder token,
                Activity target,
                Intent intent,
                 int requestCode,
                Bundle options
        )
        */

        findAndHookMethod("android.app.Instrumentation",
                lpparam.classLoader,
                "execStartActivity",
                android.content.Context.class,
                android.os.IBinder.class,
                android.os.IBinder.class,
                android.app.Activity.class,
                android.content.Intent.class,
                int.class,
                android.os.Bundle.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]Instrumentation::execStartActivity"
                                + "|" + param.args[0].toString()
                                + "|" + param.args[1].toString()
                                + "|" + param.args[2].toString()
                                + "|" + param.args[3].toString()
                                + "|" + param.args[4].toString()
                                + "|" + param.args[5].toString()
                        );

                        if (param.args[4] instanceof Intent){
                            Intent intent = (Intent)param.args[0];
                            printIntent(intent);
                        }
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]Instrumentation::execStartActivity");
                        //printStack();
                    }
                 }
        );


        // android.app.ActivityManagerNative
        // android.app.ActivityManagerProxy
        /*
        public int startActivity(
               android.app. IApplicationThread caller,
                android.content. Intent intent,
                String resolvedType,
                android.net.Uri[] grantedUriPermissions,
                int grantedMode,
                android.os.IBinder resultTo,
                String resultWho,
                int requestCode,
                boolean onlyIfNeeded,
                boolean debug,
                String profileFile,
                android.os.ParcelFileDescriptor profileFd,
                boolean autoStopProfiler
            );
            */

        //ActivityManagerService
        // android.app.IActivityManager$Stub$Proxy
        findAndHookMethod("android.app.IActivityManager$Stub$Proxy",
                lpparam.classLoader,
                "startActivity",
               "android.app.IApplicationThread", // caller
                java.lang.String.class, // callingPackage
                android.content.Intent.class, // intent
                java.lang.String.class, // resolvedType
                android.os.IBinder.class, // resultTo
                java.lang.String.class, // resultWho
                int.class, // requestCode
                int.class, // flags
                "android.app.ProfilerInfo", // profilerInfo
                //android.os.ParcelFileDescriptor.class,  // profileFd
               android.os.Bundle.class, // options
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]ActivityManagerProxy::startActivity"
                                + "|" + param.args[0].toString()
                                + "|" + param.args[1].toString()
                                + "|" + param.args[2].toString()
                                + "|" + param.args[3].toString()
                                + "|" + param.args[4].toString()
                                + "|" + param.args[5].toString()
                        );
                        if (param.args[0] instanceof Intent){
                            Intent intent = (Intent)param.args[2];
                            printIntent(intent);
                        }
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]ActivityManagerProxy::startActivity");
                        //printStack();
                    }
                }
        );

    }

    private void printStack() {
        XposedBridge.log("===Printing stack trace:");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        long id = Thread.currentThread().getId();
        for (int i = 1; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            XposedBridge.log("\tat[" + id + "]"
                    + s.getClassName() + "." + s.getMethodName()
                    + "(" + s.getFileName() + ":" + s.getLineNumber() + ")"
            );
        }
        XposedBridge.log("===Printing stack trace END.");
    }

    private void printIntent(Intent intent){
        XposedBridge.log("===Print Intent START");
        XposedBridge.log(intent.toString());
        Bundle extras = intent.getExtras();
        Set<String> keys = extras.keySet();
        for (String key:keys){
            Object obj = extras.get(key);
            XposedBridge.log("key:" + key + "|value:" + obj.toString());
        }

        XposedBridge.log("===Print Intent END.");
    }
}
