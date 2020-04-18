package com.example.a123rf

import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.*
import android.graphics.Bitmap
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter
import android.net.Uri
import android.opengl.GLSurfaceView
import android.view.View
import jp.co.cyberagent.android.gpuimage.GPUImageView


class MainActivity : AppCompatActivity() {

    private var canvasBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


   //     this.generateCanvasloop(2);

    }

    fun generateCanvasloop(sizeValue: Int) {
        var sizeGiven = sizeValue;
        var totalWidth = 400;
        var totalHeight = 400;
        val t = Thread()


        var leftRightCounterMax = sizeGiven;

        val leftArraylist: ArrayList<Int> = ArrayList()
        val rightArraylist: ArrayList<Int> = ArrayList()
        val topArraylist: ArrayList<Int> = ArrayList()
        val bottomArraylist: ArrayList<Int> = ArrayList()

        val bitmap: Bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)


        val canvas: Canvas = Canvas(bitmap)
        var shapeDrawable: ShapeDrawable
        var leftRightstart = 1;
        for (x in 1..(sizeGiven*sizeGiven)) {

            var gap = totalWidth/40;
            var marker = (x.toDouble()/sizeGiven);
            marker = Math.ceil(marker);
            topArraylist.add((0+gap+((marker-1)*totalHeight/sizeGiven)).toInt())
            bottomArraylist.add(totalHeight/sizeGiven+(0+((marker-1)*totalHeight/sizeGiven)).toInt()-gap);
            leftArraylist.add((0+gap+((leftRightstart-1)*totalWidth/sizeGiven)).toInt());
            rightArraylist.add(totalWidth/sizeGiven+(0+((leftRightstart-1)*totalWidth/sizeGiven)).toInt()-gap);

            leftRightstart++
            if(leftRightstart>leftRightCounterMax){
                leftRightstart =1;
            }
        }

        for (x in 0..leftArraylist.size-1){
            var paint = Paint()
            paint.setStyle(Paint.Style.STROKE)
            paint.setAntiAlias(true)
            paint.setDither(true)



            val d = resources.getDrawable(R.drawable.download, null)
            d.setBounds(leftArraylist[x], topArraylist[x], rightArraylist[x], bottomArraylist[x])
            d.draw(canvas)
            imageV.background = BitmapDrawable(getResources(), bitmap)
            System.out.println("X:+1");

            Thread {
                val imageUri =   Uri.parse("android.resource://com.example.a123rf/drawable/download");
                var gpuImage = GPUImage(this);
              //  gpuImage.setGLSurfaceView(findViewById<GLSurfaceView>(R.id.gpuimageview))
                gpuImage.setImage(bitmap);
                gpuImage.setFilter(GPUImageColorInvertFilter())

            }
        }

    }

    fun generateCanvas(){
        val bitmap: Bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)

        var shapeDrawable: ShapeDrawable
//1
        // rectangle positions
        var left = 20
        var top = 20
        var right = 180
        var bottom = 180

        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
        shapeDrawable.draw(canvas)
//2
        // oval positions
        left = 210
        top = 20
        right = 380
        bottom = 180

        //3
        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#009191"))
        shapeDrawable.draw(canvas)

        left = 20
        top = 200
        right = 180
        bottom = 380
//4
        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#009191"))
        shapeDrawable.draw(canvas)

        left = 210
        top = 200
        right = 380
        bottom = 380

        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.getPaint().setColor(Color.parseColor("#009191"))
        shapeDrawable.draw(canvas)

        // now bitmap holds the updated pixels

        // set bitmap as background to ImageView
        imageV.background = BitmapDrawable(getResources(), bitmap)
    }

    fun performClick2(view: View) {
        generateCanvasloop(2)
    }
    fun performClick3(view: View) {
        generateCanvasloop(3)

    }
}