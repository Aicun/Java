package nio;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.EnumSet;

public class Copy {

	static void copyFile(Path source, Path target, boolean prompt,
			boolean preserve) {
		CopyOption[] options = (preserve) ? new CopyOption[] {
				StandardCopyOption.COPY_ATTRIBUTES,
				StandardCopyOption.REPLACE_EXISTING }
				: new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };

		try {
			Files.copy(source, target, options);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.format("Unable to copy: %s: %s%n", source, e);
		}
	}

	static class TreeCopier implements FileVisitor<Path> {

		private final Path source;
		private final Path target;
		private final boolean prompt;
		private final boolean preserve;

		TreeCopier(Path source, Path target, boolean prompt, boolean preserve) {
			this.source = source;
			this.target = target;
			this.prompt = prompt;
			this.preserve = preserve;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) {
			CopyOption[] options = (preserve) ? new CopyOption[] { StandardCopyOption.COPY_ATTRIBUTES }
					: new CopyOption[0];

			Path newdir = target.resolve(source.relativize(dir));
			try {
				Files.copy(dir, newdir,	options);
			} catch (IOException e) {
				System.err.format("Unable to create: %s: %s%n", target, e);
				return FileVisitResult.SKIP_SUBTREE;
			}

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			copyFile(file, target.resolve(source.relativize(file)), prompt,
					preserve);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc)
				throws IOException {
			if (exc instanceof FileSystemLoopException) {
				System.err.println("cycle detected: " + file);
			} else {
				System.err.format("Unable to copy: %s: %s%n", file, exc);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc)
				throws IOException {
			if (exc == null && preserve) {
				Path newdir = target.resolve(source.relativize(dir));
				try {
					FileTime time = Files.getLastModifiedTime(dir);
					Files.setLastModifiedTime(newdir, time);
				} catch (IOException x) {
					System.err.format(
							"Unable to copy all attributes to: %s: %s%n",
							newdir, x);
				}
			}
			return FileVisitResult.CONTINUE;
		}

	}

	public static void main(String[] args) throws IOException {
		boolean recursive = true;
		boolean prompt = true;
		boolean preserve = true;

		Path source = Paths.get("E:\\test");

		Path target = Paths.get("E:\\game");

		// check if target is a directory
		boolean isDir = Files.isDirectory(target);

		// copy each source file/directory to target

		Path dest = (isDir) ? target.resolve(source.getFileName()) : target;

		if (recursive) {
			// follow links when copying files
			EnumSet<FileVisitOption> opts = EnumSet
					.of(FileVisitOption.FOLLOW_LINKS);
			TreeCopier tc = new TreeCopier(source, dest, prompt, preserve);
			Files.walkFileTree(source, opts, Integer.MAX_VALUE, tc);
		} else {
			// not recursive so source must not be a directory
			if (Files.isDirectory(source)) {
				System.err.format("%s: is a directory%n", source);
			}
			copyFile(source, dest, prompt, preserve);
		}

	}
}
