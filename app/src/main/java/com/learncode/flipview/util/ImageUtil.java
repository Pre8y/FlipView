package com.learncode.flipview.util;

/**
 * Created by Preeti on 25/07/16.
 */
public class ImageUtil {
//
//    Context mContext;
//    Messenger mActivityMessenger;
//    int width, height;
//    private final WeakReference<ImageView> imageViewReference;
//    public ImageUtil(Context mContext, ImageView imageView)
//    {
//        this.mContext = mContext;
//        width = imageView.getWidth();
//        height = imageView.getHeight();
//        imageViewReference = new WeakReference<ImageView>(imageView);
//    }
//
//    public  void loadImage(String id, String imgUrl, int placeHolder) {
//        if (placeHolder > 0) {
//            imageViewReference.get().setImageResource(placeHolder);
//        }
//        final File root = new File(Environment.getExternalStorageDirectory() + "/flickrTest");
//        String fileName = id+".jpg";
//        if (root.exists() && root.isDirectory()) {
//            File file = new File(root.getAbsolutePath() + "/" + fileName);
//            if(file.exists())
//            {
//                imageViewReference.get().setImageBitmap(decodeSampledBitmapFromFile(file.getAbsolutePath(), width, height));
//            }else{
//                Intent intent = new Intent(mContext, DownloadService.class);
//                Bundle data = new Bundle();
//                data.putString("img", imgUrl);
//                data.putString("name", fileName);
//                data.putParcelable(DownloadService.EXTRA_MESSENGER, new Messenger(new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        String str = (String)msg.getData().getString("image");
//                        File file = new File(root.getAbsolutePath() + "/" + str);
//                        if(file.exists())
//                        {
//                            imageViewReference.get().setImageBitmap(decodeSampledBitmapFromFile(file.getAbsolutePath(), width, height));
//                        }
//
//                    }
//                }));
//                intent.putExtras( data);
//                mContext.startService(intent);
//            }
//        }else{
//            Intent intent = new Intent(mContext, DownloadService.class);
//            Bundle data = new Bundle();
//            data.putString("img", imgUrl);
//            data.putString("name", fileName);
//            data.putParcelable(DownloadService.EXTRA_MESSENGER, new Messenger(new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    String str = (String)msg.getData().getString("image");
//                    File file = new File(root.getAbsolutePath() + "/" + str);
//                    if(file.exists())
//                    {
//                        imageViewReference.get().setImageBitmap(decodeSampledBitmapFromFile(file.getAbsolutePath(), width, height));
//                    }
//
//                }
//            }));
//            intent.putExtras(data);
//            intent.putExtra(DownloadService.EXTRA_MESSENGER, mActivityMessenger);
//            mContext.startService(intent);
//        }
//    }
//
//
//
//    public static Bitmap decodeSampledBitmapFromFile(String path,
//                                                     int reqWidth, int reqHeight) { // BEST QUALITY MATCH
//
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(path, options);
//
//        // Calculate inSampleSize
//        // Raw height and width of image
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        int inSampleSize = 1;
//
//        if (height > reqHeight) {
//            inSampleSize = Math.round((float)height / (float)reqHeight);
//        }
//
//        int expectedWidth = width / inSampleSize;
//
//        if (expectedWidth > reqWidth) {
//            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
//            inSampleSize = Math.round((float)width / (float)reqWidth);
//        }
//
//
//        options.inSampleSize = inSampleSize;
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//
//        return BitmapFactory.decodeFile(path, options);
//    }





}
