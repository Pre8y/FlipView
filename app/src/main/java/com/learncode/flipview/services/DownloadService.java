package com.learncode.flipview.services;

public class DownloadService{
//        extends Service {
//    String imageUrl;
//    String name;
//    ImageHandler mImageHandler;
//    Bundle extras;
//    public  static String EXTRA_MESSENGER = "messenger";
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//
//        Message msg = mImageHandler.obtainMessage();
//        msg.arg1 = startId;
//        extras = intent.getExtras();
//        msg.setData(intent.getExtras());
//        mImageHandler.sendMessage(msg);
//
//
//        return START_STICKY;
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//
//
//        HandlerThread thread = new HandlerThread("ServiceStartArguments",
//                Process.THREAD_PRIORITY_BACKGROUND);
//        thread.start();
//
//        Looper looper = thread.getLooper();
//        mImageHandler = new ImageHandler(looper);
//    }
//
//
//    public void downloadFile(String fileURL, String fileName) {
//
//        StatFs stat_fs = new StatFs(Environment.getExternalStorageDirectory().getPath());
//        double avail_sd_space = (double) stat_fs.getAvailableBlocks() * (double) stat_fs.getBlockSize();
//        //double GB_Available = (avail_sd_space / 1073741824);
//        double MB_Available = (avail_sd_space / 10485783);
//        //System.out.println("Available MB : " + MB_Available);
//        Log.d("MB", "" + MB_Available);
//        try {
//            File root = new File(Environment.getExternalStorageDirectory() + "/flickrTest");
//            if (root.exists() && root.isDirectory()) {
//
//            } else {
//                root.mkdir();
//            }
//            Log.d("CURRENT PATH", root.getPath());
//            URL u = new URL(fileURL);
//            HttpURLConnection c = (HttpURLConnection) u.openConnection();
//            c.setRequestMethod("GET");
//            c.setDoOutput(true);
//            c.connect();
//            int fileSize = c.getContentLength() / 1048576;
//            Log.d("FILESIZE", "" + fileSize);
//            if (MB_Available <= fileSize) {
//                c.disconnect();
//                return;
//            }
//
//            FileOutputStream f = new FileOutputStream(new File(root.getPath(), fileName));
//
//            InputStream in = c.getInputStream();
//
//
//            byte[] buffer = new byte[1024];
//            int len1 = 0;
//            while ((len1 = in.read(buffer)) > 0) {
//                f.write(buffer, 0, len1);
//            }
//            f.close();
//
//
//            if (extras!=null) {
//                Messenger messenger=(Messenger)extras.get(EXTRA_MESSENGER);
//                Message msg=Message.obtain();
//
//                msg.arg1= Activity.RESULT_OK;
//                Bundle bundle = new Bundle();
//                bundle.putString("image", fileName);
//                msg.setData(bundle);
//
//                try {
//                    messenger.send(msg);
//                }
//                catch (android.os.RemoteException e1) {
//                    Log.w(getClass().getName(), "Exception sending message", e1);
//                }
//            }
//        } catch (Exception e) {
//            Log.d("Downloader", e.getMessage());
//
//        }
//    }
//
//    private final class ImageHandler extends Handler {
//        public ImageHandler(Looper looper) {
//            super(looper);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            // Normally we would do some work here, like download a file.
//            // For our sample, we just sleep for 5 seconds.
//            try {
//                Bundle data = msg.getData();
//                String imageUrl = data.getString("img");
//                String name = data.getString("name");
//
//                downloadFile(imageUrl, name);
//
//
//            } catch (Exception e) {
//                // Restore interrupt status.
//            }
//            // Stop the service using the startId, so that we don't stop
//            // the service in the middle of handling another job
//            stopSelf(msg.arg1);
//        }
//    }

}
