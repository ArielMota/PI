package com.example.pi.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pi.ImagemPagerAdapter;
import com.example.pi.R;
import com.example.pi.manipulacao_api.APIconfig;
import com.example.pi.manipulacao_api.PopulaCarrinho;
import com.example.pi.model.Produto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProdutoListAdapter extends RecyclerView.Adapter<ProdutoListAdapter.ListViewHolder> implements Filterable {

    private List<Produto> produtos;
    private List<Produto> listaCheiaProdutos;
    private Context context;
    Dialog mDialog;
    ViewPager mViewPager;
    Activity activity;

    //RequestOptions option;

    public ProdutoListAdapter(List<Produto> produtos, Context context, Activity activity) {
        this.produtos = produtos;
        this.context = context;
        this.activity = activity;
        listaCheiaProdutos = new ArrayList<>(produtos);
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_list_produto_gridlayout,viewGroup,false);
        final ListViewHolder listViewHolder = new ListViewHolder(view);






        return listViewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {


        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.dialog_produto);




       //Captura os clicks nos cardViews
        listViewHolder.itemView.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(final View v) {
                       final Produto produto = produtos.get(position);

                       final TextView  dialog_name_pro = (TextView) mDialog.findViewById(R.id.dialog_name_id);
                       final TextView  dialog_preco_pro = (TextView) mDialog.findViewById(R.id.dialog_preco_id);
                       //carroussel
                       //final ImageView dialog_contact_img = (ImageView) mDialog.findViewById(R.id.dialog_img);
                       dialog_name_pro.setText(produtos.get(listViewHolder.getAdapterPosition()).getNome());
                       dialog_preco_pro.setText(String.valueOf(produtos.get(listViewHolder.getAdapterPosition()).getPreco()));
                       //dialog_contact_img.setImageResource(mData.get(vholder.getAdapterPosition()).getPhoto());

                       mViewPager = (ViewPager) mDialog.findViewById(R.id.pager);

                       ImagemPagerAdapter imagemPagerAdapter = new ImagemPagerAdapter(context,produto.getLista_id_imagens(),mDialog);
                        mViewPager.setAdapter(imagemPagerAdapter);

                       mDialog.show();


                       ImageView btn_exit = (ImageView) mDialog.findViewById(R.id.img_x);

                       btn_exit.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               mDialog.dismiss();
                           }
                       });

                       final TextView quantidade = (TextView) mDialog.findViewById(R.id.qnt);


                       ImageView btn_mais = (ImageView) mDialog.findViewById(R.id.dialog_btn_mais);
                       btn_mais.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               quantidade.setText(String.valueOf((Integer.valueOf((String) quantidade.getText())+ 1)) );

                           }
                       });


                       ImageView btn_menos = (ImageView) mDialog.findViewById(R.id.dialog_btn_menos);
                       btn_menos.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               quantidade.setText(String.valueOf((Integer.valueOf((String) quantidade.getText())- 1)) );

                           }
                       });



                       Button btn_adc_carrinhoo = (Button) mDialog.findViewById(R.id.dialog_btn_carrinho);

                       final ImageView img = (ImageView) mDialog.findViewById(R.id.img_produto);

                       btn_adc_carrinhoo.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {

                                String token = activity.getIntent().getExtras().getString("token");


                               Produto produto_item_carrinho = new Produto();
                               produto_item_carrinho.setId(produto.getId());
                               produto_item_carrinho.setQnt((Integer.valueOf((String) quantidade.getText())));



                               PopulaCarrinho populaCarrinho = new PopulaCarrinho();
                               populaCarrinho.populacarrinho(context,activity,view,produto_item_carrinho,token);




                                mDialog.dismiss();




                           }
                       });


                   }
               }
       );

        Produto produto = produtos.get(position);

        TextView nome_produto =  listViewHolder.nomeProduto;
        nome_produto.setText(String.valueOf(produto.getNome()));

        TextView preco_produto =  listViewHolder.precoProduto;
        preco_produto.setText(String.valueOf(produto.getPreco()));

        TextView qnt_produto =  listViewHolder.qntProduto;
        qnt_produto.setText(String.valueOf(produto.getQnt()));

        ImageView imageView = (ImageView) listViewHolder.imgProduto;
        Picasso.get().load(APIconfig.URL+"/imagem/"+produto.getLista_id_imagens().get(0)).resize(500,500)
                .centerCrop().into(imageView);


        //ImageView imagem = listViewHolder.imgProduto;
        //Resources res = context.getResources();
        //imagem.setImageResource(produtos.get(position).getImg());
        //imagem.setImageResource(produtos.get(position).getImg());

        //Glide.with(context).load(produtos.get(position).getImg()).apply(option).into(listViewHolder.imgProduto);

        //Picasso.with(context).load(produtos.get(position).getImg()).into(listViewHolder.imgProduto);
    }





    public class ListViewHolder extends  RecyclerView.ViewHolder {

        public ImageView imgProduto;
        public TextView nomeProduto;
        public TextView precoProduto;
        public TextView qntProduto;

        private CardView item_produto;



        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduto = (ImageView) itemView.findViewById(R.id.img_produto);
            nomeProduto = (TextView) itemView.findViewById(R.id.nome_produto);
            precoProduto = (TextView) itemView.findViewById(R.id.preco_produto);
            qntProduto = (TextView) itemView.findViewById(R.id.qntestoque);
            item_produto = (CardView) itemView.findViewById(R.id.produto_item_card);
        }
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    @Override
    public Filter getFilter() {

        return  produtoFiltro;
    }

    Filter produtoFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Produto> listaFiltrada = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                listaFiltrada.addAll(listaCheiaProdutos);
            }else {
                String filtroPadrao = constraint.toString().toLowerCase().trim();

                for (Produto produto : listaCheiaProdutos){
                    if (produto.getNome().toLowerCase().contains(filtroPadrao)){
                        listaFiltrada.add(produto);
                    }
                }

            }

            FilterResults resultados = new FilterResults();

            resultados.values = listaFiltrada;

            return resultados;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            produtos.clear();
            produtos.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

}
