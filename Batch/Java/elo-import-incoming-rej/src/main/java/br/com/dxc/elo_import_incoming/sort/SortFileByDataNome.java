package br.com.dxc.elo_import_incoming.sort;

import java.io.File;
import java.util.Comparator;

public class SortFileByDataNome implements Comparator<File> {

	@Override
	public int compare(File file1, File file2) {
		return getDataByNome(file1.getName()) - getDataByNome(file2.getName()); //ASC
//		return getDataByNome(file2.getName()) - getDataByNome(file1.getName()); //DESC
	}
	
	private int getDataByNome(String nome) {
		return Integer.valueOf(nome.split("-")[1]);
	}
}
