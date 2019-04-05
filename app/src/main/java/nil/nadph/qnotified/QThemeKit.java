package nil.nadph.qnotified;

import java.io.*;
import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import org.xmlpull.v1.*;
import java.util.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.annotation.*;
import de.robv.android.xposed.*;

import static nil.nadph.qnotified.Utils.log;
import static nil.nadph.qnotified.Utils.invoke_static;
import static nil.nadph.qnotified.Utils.invoke_virtual;
import static nil.nadph.qnotified.Utils.iget_object;
import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;
import static nil.nadph.qnotified.QConst.load;
import java.lang.reflect.*;

public class QThemeKit{

	static private String cachedThemeId;

	static public ColorStateList skin_gray3;
	static public ColorStateList skin_black;
	static public ColorStateList skin_red;
	static public ColorStateList skin_blue;
	static public ColorStateList skin_text_white;
	static public ColorStateList qq_setting_item_bg_nor;
	static public ColorStateList qq_setting_item_bg_pre;
	static public Drawable skin_list_normal=null,skin_list_item_unread=null,skin_list_pressed=null;
	//static public Drawable skin_tips_newmessage;

static int t=0;
	static private Context mContext;

	static private Map<String,Drawable> cachedDrawable;

	public static <SkinnableNinePatchDrawable extends Drawable> void initTheme(Context ctx) throws Throwable{
		mContext=ctx;
		if(cachedDrawable==null)cachedDrawable=new HashMap();
		String themeId=(String)invoke_static(load("com/tencent/mobileqq/theme/ThemeUtil"),"getUserCurrentThemeId",null,load("mqq/app/AppRuntime"));
		//ThemeUtil$ThemeInfo themeInfo=(ThemeUtil$ThemeInfo)invoke_static(load("com/tencent/mobileqq/theme/ThemeUtil"),"getThemeInfo",ctx,themeId,Context.class,String.class);
		//load("com/tencent/mobileqq/theme/ThemeUtil$ThemeInfo").getField(
		/*ClazzExplorer ce=ClazzExplorer.get();
		 ce.rootEle=ce.currEle=themeInfo;
		 ce.track.removeAllElements();
		 ce.init(ctx);//(Activity)ce.getCurrentActivity());//*/
		if(themeId.equals(cachedThemeId))return;

		skin_gray3
			=skin_black
			=skin_red
			=skin_blue
			=skin_text_white
			=qq_setting_item_bg_nor
			=qq_setting_item_bg_pre=null;
		skin_list_normal=skin_list_item_unread=skin_list_pressed
			=null;//=skin_tips_newmessage=null;
		String dir=locateThemeDir(themeId,ctx);
		if(dir==null){
			//log("Unable to locate theme dir!");
			//damn!
			
		}else{
			String path;
			path=findDrawableResource(dir,"skin_list_item_normal.9.png");
			if(path==null)path=findDrawableResource(dir,"skin_list_item_normal_theme_version2.9.png");
			if(path!=null)skin_list_normal=loadDrawable(path);

			path=findDrawableResource(dir,"skin_list_item_pressed.9.png");
			if(path==null)path=findDrawableResource(dir,"skin_list_item_pressed_theme_version2.9.png");
			if(path!=null)skin_list_pressed=loadDrawable(path);


			//path=findDrawableResource(dir,"skin_tips_newmessage.9.png");
			//if(path!=null)skin_tips_newmessage=loadDrawable(path);


			path=dir+"/color/";
			skin_black=findColorInXmlStupidly(path+"skin_black.xml");
			skin_red=findColorInXmlStupidly(path+"skin_red.xml");
			skin_gray3=findColorInXmlStupidly(path+"skin_gray3.xml");
			skin_text_white=findColorInXmlStupidly(path+"skin_black_item.xml");
			qq_setting_item_bg_nor=findColorInXmlStupidly(path+"qq_setting_item_bg_nor.xml");
			qq_setting_item_bg_pre=findColorInXmlStupidly(path+"qq_setting_item_bg_pre.xml");
		}
		//if(skin_tips_newmessage==null)skin_tips_newmessage= loadDrawableFromAsset("skin_tips_newmessage.9.png");
		if(skin_list_normal==null)skin_list_normal=loadDrawableFromAsset("skin_list_item_normal.9.png");
		if(skin_list_pressed==null)skin_list_pressed=loadDrawableFromAsset("skin_list_item_pressed.9.png");

		if(skin_black==null)skin_black=ColorStateList.valueOf(0xFF000000);
		if(skin_red==null)skin_red=ColorStateList.valueOf(Color.argb(255,255,70,41));
		if(skin_gray3==null)skin_gray3=ColorStateList.valueOf(Color.argb(255,128,128,128));
		if(skin_blue==null)skin_blue=ColorStateList.valueOf(Color.argb(255,0,182,249));
		if(qq_setting_item_bg_nor==null)qq_setting_item_bg_nor=ColorStateList.valueOf(Color.argb(255,249,249,251));
		if(qq_setting_item_bg_pre==null)qq_setting_item_bg_pre=ColorStateList.valueOf(Color.argb(255,192,192,192));
		
		
		
		/*Resources res=ctx.getResources();
		String pkg="com.tencent.mobileqq";
		//skin_tips_newmessage.se
		log("inner:"+skin_tips_newmessage.getIntrinsicHeight());
		skin_tips_newmessage=res.getDrawable(0x7F0220E7);
		byte[] bt=(byte[])iget_object(iget_object(iget_object(skin_tips_newmessage,"b"),"r"),"mBuffer");
		/*try{
			log("blen="+bt.length);
			FileOutputStream fout=new FileOutputStream("/sdcard/qq_dump_buffer.o");
			fout.write(bt,0,bt.length);
			fout.flush();
			fout.close();
			fout=new FileOutputStream("/sdcard/qq_dump_ninebuffer.o");
			 bt=(byte[])iget_object(iget_object(iget_object(skin_tips_newmessage,"b"),"r"),"mNinePatchChunk");
			
			fout.write(bt,0,bt.length);
			fout.flush();
			fout.close();
			//log("wtf="+BitmapFactory.decodeByteArray(bt,0,bt.length).getHeight());
			//log("Den="+bt.getDensity());
		}catch(Exception e){
			log(e.toString());
		}
		log("official:"+skin_tips_newmessage.getIntrinsicHeight());
		//ClazzExplorer ce=ClazzExplorer.get();
		//ce.init((Activity)ctx);
		//ce.currEle=ce.rootEle=skin_tips_newmessage;
		
		
		
		
		skin_list_normal=res.getDrawable(res.getIdentifier("skin_list_item_normal","drawable",pkg));
		skin_list_pressed=res.getDrawable(res.getIdentifier("skin_list_item_pressed","drawable",pkg));
		skin_tips_newmessage=res.getDrawable(res.getIdentifier("skin_tips_newmessage","drawable",pkg));
		skin_black=res.getColorStateList(res.getIdentifier("skin_black","color",pkg));
		skin_red=res.getColorStateList(res.getIdentifier("skin_red","color",pkg));
		skin_gray3=res.getColorStateList(res.getIdentifier("skin_gray3","color",pkg));
		skin_text_white=res.getColorStateList(res.getIdentifier("skin_black_item","color",pkg));
		qq_setting_item_bg_nor=res.getColorStateList(res.getIdentifier("qq_setting_item_bg_nor","color",pkg));
		qq_setting_item_bg_pre=res.getColorStateList(res.getIdentifier("qq_setting_item_bg_pre","color",pkg));
		//*/
	}

	public static StateListDrawable getListItemBackground(){
		StateListDrawable sd=new StateListDrawable();
        sd.addState(new int[]{android.R.attr.state_pressed},skin_list_pressed);  
        //sd.addState(new int[]{android.R.attr.state_focused},skin_list_pressed);  
        sd.addState(new int[]{android.R.attr.state_selected},skin_list_pressed);   
        sd.addState(new int[]{},skin_list_normal);
		return sd;
	}

	public static InputStream openAsset(String name){
		return QThemeKit.class.getClassLoader().getResourceAsStream("assets/"+name);
	}

	public static String findDrawableResource(String dir,String name){
		DisplayMetrics metric = new DisplayMetrics();
		((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		String dpistr="nodpi";//wtf
		if(densityDpi<140)dpistr="ldpi";//120
		else if(densityDpi<200)dpistr="mdpi";//160
		else if(densityDpi<260)dpistr="hdpi";//240
		else if(densityDpi<260)dpistr="xhdpi";//320
		else dpistr="xxhdpi";//480
		String path=dir+"/drawable-"+dpistr+"/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-xhdpi/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-xxhdpi/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-hdpi/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-mdpi/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-ldpi/"+name;
		if(new File(path).exists())return path;
		path=dir+"/drawable-nodpi/"+name;
		if(new File(path).exists())return path;
		return null;
	}

	public static Drawable loadDrawableFromAsset(String name){
		return loadDrawableFromAsset(name,null);
	}

	public static Drawable loadDrawable(String path){
		return loadDrawable(path,null);
	}
	
	public static Drawable loadDrawableFromStream(InputStream in,String name,@Nullable Resources res){
		Drawable ret;
		try{
			
			
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			/*
			// 取得想要缩放的matrix参数
			Matrix matrix = new Matrix();
			matrix.postScale(1.32f, 1.32f);
			// 得到新的图片
			Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix,
											   true);
			
			*
			
			
			log("den="+res.getDisplayMetrics().density);
			log("sden="+res.getDisplayMetrics().scaledDensity);
			log("denDpi="+res.getDisplayMetrics().densityDpi);
			//log("den="+res.getDisplayMetrics());*/
			
			
			bitmap.setDensity(res.getDisplayMetrics().densityDpi);
			//log(name+"BiHeight:"+bitmap.getHeight());
			byte[] chunk = bitmap.getNinePatchChunk();
			//log("Res == "+res);
			if(NinePatch.isNinePatchChunk(chunk)){
				Class clz=QConst.load("com/tencent/theme/SkinnableNinePatchDrawable");
				ret=(Drawable)XposedHelpers.findConstructorBestMatch(clz,Resources.class,Bitmap.class,byte[].class,Rect.class,String.class)
					.newInstance(res,bitmap,chunk,new Rect(),name);
			}else{
				ret=new BitmapDrawable(res,bitmap);
			}
			
			//log(name+"DrHiMin="+ret.getMinimumHeight());
			return ret;
		}catch(Exception e){
			XposedBridge.log(e);
		}
		return null;
	}

	public static Drawable loadDrawableFromAsset(String name,@Nullable Resources res){
		Drawable ret;
		if((ret=cachedDrawable.get(name))!=null)return ret;
		try{
			if(res==null&&mContext!=null)res=mContext.getResources();
			log(res+"is not null");
			InputStream fin=openAsset(name);
			ret=loadDrawableFromStream(fin,name,res);
			cachedDrawable.put(name,ret);
			return ret;
		}catch(Exception e){
			XposedBridge.log(e);
		}
		return null;
	}

	public static Drawable loadDrawable(String path,@Nullable Resources res){
		Drawable ret;
		if((ret=cachedDrawable.get(path))!=null)return ret;
		try{
			if(res==null&&mContext!=null)res=mContext.getResources();
			FileInputStream fin=new FileInputStream(path);
			ret=loadDrawableFromStream(fin,path,res);
			cachedDrawable.put(path,ret);
			return ret;
		}catch(FileNotFoundException e){}
		return null;
	}



	private static String locateThemeDir(String themeId,Context ctx){
		File themesDir=ctx.getDir("mobileqq_theme",Context.MODE_PRIVATE);
		//if(!themesDir.isDirectory())return null;
		File[] themes=themesDir.listFiles();
		for(int i=0;i<themes.length;i++){
			//log(themes[i].getName());
			if(themes[i].getName().startsWith(themeId+"_")&&themes[i].isDirectory()){
				return themes[i].getAbsolutePath();
			}
		}
		return null;
	}


	@Deprecated
	public static ColorStateList findColorInXmlStupidly(String file){
		byte[] byteSrc=null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try{
			fis=new FileInputStream(file);
			bos=new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			byteSrc=bos.toByteArray();
			//}catch(Exception e){
			//Utils.log("parse xml error:"+e.toString());
			if(byteSrc==null)throw new IOException(file);
			if(byteSrc.length!=0x158)throw new IOException("0x158 bytes expected, "+byteSrc.length+" got.");
			int color=ub2i(byteSrc[0x10C])|ub2i(byteSrc[0x10D])<<8|ub2i(byteSrc[0x10E])<<16|ub2i(byteSrc[0x10F])<<24;
			//Utils.log("Decoded:"+Integer.toHexString(color));
			return ColorStateList.valueOf(color);
		}catch(Exception e){
			try{
				fis.close();
				bos.close();
			}catch(Exception e2){}
		}
		return null;
	}//*/

	/* unsigned byte to int */
	public static int ub2i(byte b){
		return b<0?(b+256):b;
	}



}
