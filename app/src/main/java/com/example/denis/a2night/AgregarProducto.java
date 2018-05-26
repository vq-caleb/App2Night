package com.example.denis.a2night;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarProducto extends Fragment {
    private static final int SELECT_PHOTO = 100;
    Uri selectedImage;
    FirebaseStorage storage;
    StorageReference storageRef,imageRef;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    private Bitmap my_image;
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    private List<Producto> misObjetos = new ArrayList<Producto>();
    View view;
    EditText nombre, precio;
    Button button,seleccionaImagen;
    ImageView imagenSeleccionada;
    String productoActual;
    public AgregarProducto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);
        this.misObjetos = aGlobal.getProductosEmpresaActual();

        button = (Button) view.findViewById(R.id.agregar);
        nombre = (EditText) view.findViewById(R.id.nombreProduc);
        precio = (EditText) view.findViewById(R.id.precioProduc);
        seleccionaImagen = (Button) view.findViewById(R.id.subir);

        seleccionaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{ android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }
            }
        });


        imagenSeleccionada = (ImageView) view.findViewById(R.id.imageView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto(nombre.getText().toString(), precio.getText().toString());
            }
        });
        storage = FirebaseStorage.getInstance();
        //creates a storage reference
        storageRef = storage.getReference();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getActivity(),"Imagen Seleccionada, click en subir",Toast.LENGTH_SHORT).show();
                    selectedImage = imageReturnedIntent.getData();
                    Picasso.with(getActivity()).load(selectedImage).into(imagenSeleccionada);
                }
        }
    }


    private void agregarProducto(final String nombre2, String precio2) {
        myRef = db.getReference().child("Menu").child(aGlobal.getIdEmpresaActual());
            Producto producto = new Producto(nombre2, precio2, "");
            myRef.child(""+(misObjetos.size()+1)).setValue(producto);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    productoActual = nombre2;
                    uploadImage();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
    }

    public void selectImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


    public void uploadImage() {
        Mensaje(this.storageRef.toString());
        //create reference to images folder and assing a name to the file that will be uploaded
        imageRef = storageRef.child("menus/"+aGlobal.getIdEmpresaActual()+"/"+this.productoActual);
        Mensaje(this.imageRef.toString());
        //creating and showing progress dialog
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);
        //starting upload
        uploadTask = imageRef.putFile(selectedImage);
        // Observe state change events such as progress, pause, and resume
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //sets and increments value of progressbar
                progressDialog.incrementProgressBy((int) progress);
            }
        });
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getActivity(),"Error en la carga!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(getActivity(),"Carga completa",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Mensaje("Producto agregado");
                nombre.setText("");
                precio.setText("");
                //showing the uploaded image in ImageView using the download url
                Picasso.with(getActivity()).load(downloadUrl).into(imagenSeleccionada);
            }
        });
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    };


}
