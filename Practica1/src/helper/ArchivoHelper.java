package helper;

import java.io.File;

public class ArchivoHelper {
	public static void eliminarArchivos(String ruta) {
		try {
			File dir = new File(ruta);
			if (dir.exists() && dir.isDirectory())
				for (File file : dir.listFiles()) {
					if (file.isFile())
						file.delete();
				}

		} catch (Exception ex) {
		}
	}
}
