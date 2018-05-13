package testxposed.zeewinpeng.com.testxposed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.io.File;
import java.util.ArrayList;
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
        //(java.util.List<java.io.File>).class;
        XposedBridge.log("hooked app: " + lpparam.packageName);
        mPackageName = lpparam.packageName;
        if (mPackageName.startsWith("com.tencent.gamestick")){
            findAndHookMethod(
                    "com.tencent.nnw.loader.app.b.c",
                    lpparam.classLoader,
                    "b",
                    java.lang.ClassLoader.class,
                    java.util.List.class,
                    java.io.File.class,
                    new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("[B]app.b.c.b");
                    printStack();
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("[B]app.b.c.b");
                    //printStack();
                }
            });

            findAndHookMethod(
                    "com.tencent.nnw.loader.app.b.c",
                    lpparam.classLoader,
                    "c",
                    Object.class,
                    java.util.ArrayList.class,
                    java.io.File.class,
                    java.util.ArrayList.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("[B]app.b.c.c");
                            printStack();
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("[B]app.b.c.c");
                            //printStack();
                        }
                    });

            findAndHookMethod(
                    "com.tencent.nnw.loader.app.b",
                    lpparam.classLoader,
                    "c",
                    Object.class,
                    java.lang.String.class,
                    Object[].class,

                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("[B]app.b.c");
                            printStack();
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("[B]app.b.c");
                            //printStack();
                        }
                    });
        }

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

        // android.app.Activity::startActivityForResult
        /*
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
        */

        // android.content.Intent::setDataAndType
        /*
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
        */

        // android.content.Intent::putExtra
        /*
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
        */

        // android.content.Intent::setExtrasClassLoader
        /*
        findAndHookMethod(
                "android.content.Intent",
                lpparam.classLoader,
                "setExtrasClassLoader",
                java.lang.ClassLoader.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];
                        if (obj != null){
                            XposedBridge.log("[B]<" + mPackageName + ">Intent::setExtrasClassLoader");
                            XposedBridge.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            XposedBridge.log("ClassLoader:" + param.args[0]);
                            XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            printStack();
                        }
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];
                        if (obj != null) {
                            XposedBridge.log("[A]<" + mPackageName + ">Intent::setExtrasClassLoader");
                            //printStack();
                        }
                    }
                }
        );
        */

        // ActivityThread::sendMessage
        /*
        findAndHookMethod(
                "android.app.ActivityThread",
                lpparam.classLoader,
                "sendMessage",
                int.class,
                java.lang.Object.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        int what = (int)param.args[0];
                        if (what == 100){
                            XposedBridge.log("[B]<" + mPackageName + ">ActivityThread::sendMessage");
                            Object obj = param.args[1];
                            XposedBridge.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            XposedBridge.log("ActivityClientRecord:" + obj);
                            XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            printStack();
                        }

                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        int what = (int)param.args[0];
                        if (what == 100) {
                            XposedBridge.log("[A]<" + mPackageName + ">ActivityThread::sendMessage");
                        }
                        //printStack();
                    }
                }
        );
        */

        // ActivityThread::handleLaunchActivity
        /*
        findAndHookMethod(
                "android.app.ActivityThread",
                lpparam.classLoader,
                "handleLaunchActivity",
                "android.app.ActivityThread.ActivityClientRecord",
                android.content.Intent.class,
                java.lang.String.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];

                        if (obj != null){
                            XposedBridge.log("[B]<" + mPackageName + ">ActivityThread::handleLaunchActivity");
                            XposedBridge.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            XposedBridge.log("ActivityClientRecord:" + obj);
                            XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            printStack();
                        }

                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];

                        if (obj != null){
                            XposedBridge.log("[A]<" + mPackageName + ">ActivityThread::handleLaunchActivity");
                        }
                        //printStack();
                    }
                }
        );
        */

        // ActivityThread::handleLaunchActivity
        /*
        findAndHookMethod(
                "android.app.ActivityThread",
                lpparam.classLoader,
                "performLaunchActivity",
                "android.app.ActivityThread.ActivityClientRecord",
                android.content.Intent.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];

                        if (obj != null){
                            XposedBridge.log("[B]<" + mPackageName + ">ActivityThread::performLaunchActivity");
                            XposedBridge.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            XposedBridge.log("ActivityClientRecord:" + obj);
                            XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            printStack();
                        }

                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object obj = param.args[0];

                        if (obj != null){
                            XposedBridge.log("[A]<" + mPackageName + ">ActivityThread::performLaunchActivity");
                        }
                        //printStack();
                    }
                }
        );
        */

        // ActivityThread.H handleMessage
        /*
        findAndHookMethod(
                "android.app.ActivityThread.H",
                lpparam.classLoader,
                "handleMessage",
                android.os.Message.class,
                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                        android.os.Message msg = (android.os.Message)param.args[0];

                        if (msg !=null && msg.what == 100){
                            XposedBridge.log("[B]<" + mPackageName + ">ActivityThread.H::handleMessage");
                            XposedBridge.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            XposedBridge.log("ActivityClientRecord:" + msg.obj);
                            XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            printStack();
                        }

                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        android.os.Message msg = (android.os.Message)param.args[0];

                        if (msg != null && msg.what == 100) {
                            XposedBridge.log("[A]<" + mPackageName + ">ActivityThread.H::handleMessage");
                            //printStack();
                        }
                    }
                }
        );
        */



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

        // android.app.Intrumentation::execStartActivity
        /*
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
        */




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
        /*
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

                        intent.setExtrasClassLoader(null);
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
        */

        // Instrumentation::newActivity
        findAndHookMethod("android.app.Instrumentation",
                lpparam.classLoader,
                "newActivity",
                java.lang.ClassLoader.class,
                java.lang.String.class,
                android.content.Intent.class,

                new XC_MethodHook(){
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[B]<" + mPackageName + ">Instrumentation::newActivity");

                        ClassLoader cl = (ClassLoader)param.args[0];
                        String className = (String)param.args[1];
                        Intent intent = (Intent)param.args[2];
                        XposedBridge.log("ClassLoader:" + cl);
                        XposedBridge.log("className:" + className);
                        XposedBridge.log("intent:" + (intent==null ? "null":intent));

                        printIntent(intent);

                        printStack();


                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("[A]<" + mPackageName + ">Instrumentation::newActivity");
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
