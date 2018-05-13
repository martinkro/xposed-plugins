package testxposed.zeewinpeng.com.testxposed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class TestXposed implements IXposedHookLoadPackage {
    String mPackageName = "test";
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
        mPackageName = lpparam.packageName;

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
                        XposedBridge.log("[B]<" + mPackageName + ">startActivityForResult");
                        if (param.args[0] instanceof Intent){
                            Intent intent = (Intent)param.args[0];
                            printIntent(intent);
                        }
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">startActivityForResult");
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
                        XposedBridge.log("[B]<" + mPackageName + ">setDataAndType " + param.args[0]  + "|" + param.args[1]);
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">setDataAndType");
                        //printStack();
                    }
        });

        findAndHookMethod(
                "android.content.Intent",
                lpparam.classLoader,
                "putExtra",
                java.lang.String.class,
                android.os.Parcelable.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]<" + mPackageName + ">Intent::putExtra " + param.args[0]  + "|" + param.args[1]);
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">Intent::putExtra");
                        //printStack();
                    }
                }
        );

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
                        XposedBridge.log("[B]<" + mPackageName + ">Instrumentation::execStartActivity");

                        Context who = (Context)param.args[0];
                        IBinder contextThread = (IBinder)param.args[1];
                        IBinder token = (IBinder)param.args[2];
                        Activity target = (Activity)param.args[3];
                        Intent intent = (Intent)param.args[4];
                        int requestCode = (int)param.args[5];
                        Bundle options = (Bundle)param.args[6];
                        XposedBridge.log("who:" + (who==null ? "null":who));
                        XposedBridge.log("contextThread:" + (contextThread==null ? "null":contextThread));
                        XposedBridge.log("token:" + (token==null ? "null":token));
                        XposedBridge.log("target:" + (target==null ? "null":target));
                        XposedBridge.log("intent:" + (intent==null ? "null":intent));
                        XposedBridge.log("requestCode:" + requestCode);
                        XposedBridge.log("options:" + (options==null ? "null":options));
                        printIntent(intent);
                        if(target !=null){
                            ClassLoader cl = target.getClassLoader();
                            XposedBridge.log("[" + mPackageName + "]ClassLoader:" + cl);
                        }
                        printStack();


                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">Instrumentation::execStartActivity");
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
                        XposedBridge.log("[B]<" + mPackageName + ">ActivityManagerProxy::startActivity");

                        XposedBridge.log("<<<<<<<<<<<<<<<<<<<<");
                        XposedBridge.log("caller:" + (param.args[0] == null ? "null":param.args[0]));
                        String callingPackage = (String)param.args[1];
                        Intent intent = (Intent)param.args[2];
                        String resolveType = (String)param.args[3];
                        String resultWho = (String)param.args[5];
                        XposedBridge.log("callingPackage:" + callingPackage);
                        XposedBridge.log("intent:" + intent);
                        XposedBridge.log("resolveType:" + resolveType);
                        XposedBridge.log("resultWho:" + resultWho);
                        printIntent(intent);
                        XposedBridge.log(">>>>>>>>>>>>>>>>>>>>");
                        printStack();
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">ActivityManagerProxy::startActivity");
                        XposedBridge.log("<<<<<<<<<<<<<<<<<<<<");
                        XposedBridge.log("caller:" + (param.args[0] == null ? "null":param.args[0]));
                        String callingPackage = (String)param.args[1];
                        Intent intent = (Intent)param.args[2];
                        String resolveType = (String)param.args[3];
                        String resultWho = (String)param.args[5];
                        XposedBridge.log("callingPackage:" + callingPackage);
                        XposedBridge.log("intent:" + intent);
                        XposedBridge.log("resolveType:" + resolveType);
                        XposedBridge.log("resultWho:" + resultWho);
                        printIntent(intent);
                        XposedBridge.log(">>>>>>>>>>>>>>>>>>>>");
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
        if (intent != null){
            XposedBridge.log(intent.toString());

            Bundle extras = intent.getExtras();
            if(extras != null){
                Set<String> keys = extras.keySet();
                if (keys != null){
                    for (String key:keys){
                        Object obj = extras.get(key);
                        if (obj != null){
                            if (obj.getClass().equals(java.lang.String.class)){
                                XposedBridge.log("key:" + key + "|value:" + obj);
                            }else{
                                XposedBridge.log("key:" + key + "|value:" + obj.toString());
                            }
                        }

                    }
                }
            }


        }else{
            XposedBridge.log("intent is null");
        }


        XposedBridge.log("===Print Intent END.");
    }
}
