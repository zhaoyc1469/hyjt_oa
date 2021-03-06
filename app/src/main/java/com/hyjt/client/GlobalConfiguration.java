package com.hyjt.client;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.hyjt.client.mvp.model.cache.CommonCache;
import com.hyjt.client.mvp.model.service.AppService;
import com.hyjt.client.mvp.model.service.CommonService;
import com.hyjt.frame.api.Api;
import com.hyjt.frame.base.delegate.AppDelegate;
import com.hyjt.frame.di.module.GlobalConfigModule;
import com.hyjt.frame.http.GlobalHttpHandler;
import com.hyjt.frame.integration.ConfigModule;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.utils.UiUtils;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.HttpException;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

/**
 * app的全局配置信息在此配置,需要将此实现类声明到AndroidManifest中
 */


public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl(Api.APP_DOMAIN)
                .globalHttpHandler(new GlobalHttpHandler() {
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {

                        return response;
                    }

                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {

                        SharedPreferences sharedPre= context.getSharedPreferences("config", MODE_PRIVATE);
                        String sessionid = sharedPre.getString("sessionid", "");
                        Request cookie = request.newBuilder().header("Cookie", "ASP.NET_SessionId=" + sessionid)
                                .build();
                        return cookie;
                    }
                })
                .responseErrorListener((context1, t) -> {
                    /* 用来提供处理所有错误的监听
                       rxjava必要要使用ErrorHandleSubscriber(默认实现Subscriber的onError方法),此监听才生效 */
                    Timber.tag("Catch-Error").w(t.getMessage());
                    String msg = t.getMessage();
//                    String msg = "未知错误";
                    if (t instanceof UnknownHostException) {
                        msg = "网络不可用";
                    } else if (t instanceof SocketTimeoutException) {
                        msg = "请求网络超时";
                    } else if (t instanceof HttpException) {
                        HttpException httpException = (HttpException) t;
                        msg = convertStatusCode(httpException);
                    } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
                        msg = "数据解析错误";
                    }
                    UiUtils.snackbarText(msg);
                    Toast.makeText(context1, msg, Toast.LENGTH_SHORT).show();

                })
                .gsonConfiguration((context1, gsonBuilder) -> {//这里可以自己自定义配置Gson的参数
                    gsonBuilder
                            .serializeNulls()//支持序列化null的参数
                            .enableComplexMapKeySerialization();//支持将序列化key为object的map,默认只能序列化key为string的map
                })
                .retrofitConfiguration((context1, retrofitBuilder) -> {//这里可以自己自定义配置Retrofit的参数,甚至你可以替换系统配置好的okhttp对象
//                    retrofitBuilder.addConverterFactory(FastJsonConverterFactory.create());//比如使用fastjson替代gson
                })
                .okhttpConfiguration((context1, okhttpBuilder) -> {//这里可以自己自定义配置Okhttp的参数
                    okhttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
                    //开启使用一行代码监听 Retrofit／Okhttp 上传下载进度监听,以及 Glide 加载进度监听 详细使用方法查看 https://github.com/JessYanCoding/ProgressManager
//                    ProgressManager.getInstance().with(okhttpBuilder);
                })
                .rxCacheConfiguration((context1, rxCacheBuilder) -> {//这里可以自己自定义配置RxCache的参数
                    rxCacheBuilder.useExpiredDataIfLoaderNotAvailable(true);
                });
    }

    @Override
    public void registerComponents(Context context, IRepositoryManager repositoryManager) {
        repositoryManager.injectRetrofitService(CommonService.class, AppService.class);
        repositoryManager.injectCacheService(CommonCache.class);
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppDelegate.Lifecycle> lifecycles) {
        // AppDelegate.Lifecycle 的所有方法都会在基类Application对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        lifecycles.add(new AppDelegate.Lifecycle() {

            @Override
            public void onCreate(Application application) {
//                if (BuildConfig.LOG_DEBUG) {//Timber日志打印
//                    Timber.plant(new Timber.DebugTree());
//                }
//                //leakCanary内存泄露检查
//                ((App) application).getAppComponent().extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
            }

            @Override
            public void onTerminate(Application application) {

            }
        });
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                Timber.w(activity + " - onActivityCreated");
//                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
//                if (activity.findViewById(R.id.toolbar) != null) {
//                    if (activity instanceof AppCompatActivity) {
//                        ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
//                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
//                    } else {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
//                            activity.getActionBar().setDisplayShowTitleEnabled(false);
//                        }
//                    }
//                }
//                if (activity.findViewById(R.id.toolbar_title) != null) {
//                    ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
//                }
//                if (activity.findViewById(R.id.toolbar_back) != null) {
//                    activity.findViewById(R.id.toolbar_back).setOnClickListener(v -> {
//                        activity.onBackPressed();
//                    });
//                }
            }


            @Override
            public void onActivityStarted(Activity activity) {
                Timber.w(activity + " - onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Timber.w(activity + " - onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Timber.w(activity + " - onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Timber.w(activity + " - onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Timber.w(activity + " - onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Timber.w(activity + " - onActivityDestroyed");
            }
        });
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                // 在配置变化的时候将这个 Fragment 保存下来,在 Activity 由于配置变化重建是重复利用已经创建的Fragment。
                // https://developer.android.com/reference/android/app/Fragment.html?hl=zh-cn#setRetainInstance(boolean)
                // 在 Activity 中绑定少量的 Fragment 建议这样做,如果需要绑定较多的 Fragment 不建议设置此参数,如 ViewPager 需要展示较多 Fragment
                f.setRetainInstance(true);
            }

            @Override
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                //这里应该是检测 Fragment 而不是 FragmentLifecycleCallbacks 的泄露。
//                ((RefWatcher) ((App) f.getActivity()
//                        .getApplication())
//                        .getAppComponent()
//                        .extras()
//                        .get(RefWatcher.class.getName()))
//                        .watch(f);
            }
        });
    }


    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
