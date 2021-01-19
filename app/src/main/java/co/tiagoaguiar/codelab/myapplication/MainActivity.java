package co.tiagoaguiar.codelab.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	//private View btnImc;
	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*
		btnImc = findViewById(R.id.btn_imc_activity);
		btnImc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent imcAcitivity = new Intent(getBaseContext(), ImcActivity.class);
				startActivity(imcAcitivity);
			}
		});*/
		List<MainItem> mainItens = new ArrayList<>();
		mainItens.add(new MainItem(1, R.drawable.ic_baseline_wb_sunny_24, R.string.label_imc, Color.GREEN));
		mainItens.add(new MainItem(2, R.drawable.ic_baseline_visibility_24, R.string.label_tmb, Color.RED));

		rvMain = findViewById(R.id.rv_main);
		rvMain.setLayoutManager(new LinearLayoutManager(this));
		MainAdapter adapter = new MainAdapter(mainItens);
		rvMain.setAdapter(adapter);


	}


	private class MainAdapter extends RecyclerView.Adapter<MainViewholder>{
		private List<MainItem> mainItems;

		public MainAdapter(List<MainItem> mainItems){
			this.mainItems = mainItems;
		}

		@NonNull
		@Override
		public MainViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			//View layoutitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
			return new MainViewholder(getLayoutInflater().inflate(R.layout.main_item, parent,false));
			//return new MainViewholder(layoutitem);
		}

		@Override
		public void onBindViewHolder(@NonNull MainViewholder holder, int position) {

			MainItem mainItemCurrent = mainItems.get(position);
			holder.bind(mainItemCurrent);
		}

		@Override
		public int getItemCount() {
			return mainItems.size();
		}
	}

	private class MainViewholder extends RecyclerView.ViewHolder{

		public MainViewholder(@NonNull View itemView) {
			super(itemView);
		}
		public void bind(MainItem item){
			//recupera dentro do ViewHolder
			TextView txt_rv = itemView.findViewById(R.id.txt_rv);
			ImageView img_rv = itemView.findViewById(R.id.icon_imc);
			LinearLayout container	=	(LinearLayout) itemView;

			//seta dentro do viewholder
			txt_rv.setText(item.getTextStringId());
			img_rv.setImageResource(item.getDrawable());
			container.setBackgroundColor(item.getColor());
		}

	}

}