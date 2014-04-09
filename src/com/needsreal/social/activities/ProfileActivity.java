package com.needsreal.social.activities;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.needsreal.social.R;
import com.needsreal.social.fragments.ProfileTab1Fragment;
import com.needsreal.social.fragments.ProfileTab2Fragment;
import com.needsreal.social.fragments.ProfileTab3Fragment;
import com.needsreal.social.ui_utils.ProfilePagerAdapter;


public class ProfileActivity extends FragmentActivity
{
	private ProfilePagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);

		// Windows properties
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.setContentView(R.layout.activity_profile);

		// Création de la liste de Fragments que fera défiler le PagerAdapter
		List<Fragment> fragments = new Vector<Fragment>();

		// Ajout des Fragments dans la liste
		fragments.add(Fragment.instantiate(this,ProfileTab1Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this,ProfileTab2Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this,ProfileTab3Fragment.class.getName()));

		// Création de l'adapter qui s'occupera de l'affichage de la liste de
		// Fragments
		this.mPagerAdapter = new ProfilePagerAdapter(super.getSupportFragmentManager(),this.getApplicationContext () , fragments);

		ViewPager pager = (ViewPager) super.findViewById(R.id.pager);
		// Affectation de l'adapter au ViewPager
		pager.setAdapter(this.mPagerAdapter);
	}

}
