package mypkg;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class _ByteUnitStringインデックステスト {
	
	@Test
	public void 指定インデックスがきりのいい文字を差しているか正しい真偽値を返すこと_その１() {
		// きりのいいインデックス判定（isProperIndex）が正しい真偽値を返すこと。
		ByteUnitString obj = new ByteUnitString("あいう");
		
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 0));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 3));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 6));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 1));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 2));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 4));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 5));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 7));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 8));
		
		try {
			ByteUnitString.isProperIndex(obj.toString(), -1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生テストが成功しました。
		}
		try {
			ByteUnitString.isProperIndex(obj.toString(), 11);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生テストが成功しました。
		}
	}
	
	@Test
	public void 指定インデックスがきりのいい文字を差しているか正しい真偽値を返すこと_その２() {
		// きりのいいインデックス判定（isProperIndex）が正しい真偽値を返すこと。
		ByteUnitString obj = new ByteUnitString("あ22いう");
		
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 0));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 3));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 4));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 5));
		assertTrue(ByteUnitString.isProperIndex(obj.toString(), 8));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 1));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 2));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 6));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 7));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 9));
		assertFalse(ByteUnitString.isProperIndex(obj.toString(), 10));
		
		try {
			ByteUnitString.isProperIndex(obj.toString(), -1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			ByteUnitString.isProperIndex(null, 0);
			fail();
		}catch (IllegalArgumentException e) {
			// 例外発生が成功。
		}
		try {
			ByteUnitString.isProperIndex(obj.toString(), 11);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}
	
	@Test
	public void 次のきりのいい文字のインデックス取得＿半角() {
		int actual = -1;
		int expect = -1;
		ByteUnitString obj = new ByteUnitString("abcdef");
		
		expect = 1;
		actual = obj.nextProperIndex(0);
		assertThat(actual, is(expect));
		
		expect = 2;
		actual = obj.nextProperIndex(1);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.nextProperIndex(2);
		assertThat(actual, is(expect));
		
		expect = 4;
		actual = obj.nextProperIndex(3);
		assertThat(actual, is(expect));
		
		expect = 5;
		actual = obj.nextProperIndex(4);
		assertThat(actual, is(expect));
		
		expect = -1;
		actual = obj.nextProperIndex(5);
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 次のきりのいい文字のインデックス取得で例外発生＿半角() {
		ByteUnitString obj = new ByteUnitString("abcdef");
		
		try {
			obj.nextProperIndex(-1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			obj.nextProperIndex(6);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}
	
	@Test
	public void 次のきりのいい文字のインデックス取得＿全角() {
		int actual = -1;
		int expect = -1;
		ByteUnitString obj = new ByteUnitString("あいう");
		
		expect = 3;
		actual = obj.nextProperIndex(0);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.nextProperIndex(1);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.nextProperIndex(2);
		assertThat(actual, is(expect));
		
		expect = 6;
		actual = obj.nextProperIndex(3);
		assertThat(actual, is(expect));
		
		expect = 6;
		actual = obj.nextProperIndex(4);
		assertThat(actual, is(expect));
		
		expect = 6;
		actual = obj.nextProperIndex(5);
		assertThat(actual, is(expect));
		
		expect = -1;
		actual = obj.nextProperIndex(6);
		assertThat(actual, is(expect));
		
		expect = -1;
		actual = obj.nextProperIndex(7);
		assertThat(actual, is(expect));
		
		expect = -1;
		actual = obj.nextProperIndex(8);
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 次のきりのいい文字のインデックス取得で例外発生＿全角() {
		ByteUnitString obj = new ByteUnitString("あいう");
		
		try {
			obj.nextProperIndex(-1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			obj.nextProperIndex(9);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}
	
	@Test
	public void 前のきりのいい文字のインデックス取得＿半角() {
		int actual = -1;
		int expect = -1;
		ByteUnitString obj = new ByteUnitString("abcdef");
		
		expect = -1;
		actual = obj.previousProperIndex(0);
		assertThat(actual, is(expect));
		
		expect = 0;
		actual = obj.previousProperIndex(1);
		assertThat(actual, is(expect));
		
		expect = 1;
		actual = obj.previousProperIndex(2);
		assertThat(actual, is(expect));
		
		expect = 2;
		actual = obj.previousProperIndex(3);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.previousProperIndex(4);
		assertThat(actual, is(expect));
		
		expect = 4;
		actual = obj.previousProperIndex(5);
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 前のきりのいい文字のインデックス取得で例外発生＿半角() {
		ByteUnitString obj = new ByteUnitString("abcdef");
		
		try {
			obj.previousProperIndex(-1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			obj.previousProperIndex(6);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}
	
	@Test
	public void 前のきりのいい文字のインデックス取得＿全角() {
		int actual = -1;
		int expect = -1;
		ByteUnitString obj = new ByteUnitString("あいう");
		
		expect = -1;
		actual = obj.previousProperIndex(0);
		assertThat(actual, is(expect));
		
		expect = 0;
		actual = obj.previousProperIndex(1);
		assertThat(actual, is(expect));
		
		expect = 0;
		actual = obj.previousProperIndex(2);
		assertThat(actual, is(expect));
		
		expect = 0;
		actual = obj.previousProperIndex(3);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.previousProperIndex(4);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.previousProperIndex(5);
		assertThat(actual, is(expect));
		
		expect = 3;
		actual = obj.previousProperIndex(6);
		assertThat(actual, is(expect));
		
		expect = 6;
		actual = obj.previousProperIndex(7);
		assertThat(actual, is(expect));
		
		expect = 6;
		actual = obj.previousProperIndex(8);
		assertThat(actual, is(expect));
		
	}
	
	@Test
	public void 前のきりのいい文字のインデックス取得で例外発生＿全角() {
		ByteUnitString obj = new ByteUnitString("あいう");
		
		try {
			obj.previousProperIndex(-1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
		try {
			obj.previousProperIndex(9);
			fail();
		}catch (IndexOutOfBoundsException e) {
			// 例外発生が成功。
		}
	}

	@Test
	public void 空文字のインデックスのテスト() {
		// 空文字ならインデックスがいくつでも範囲外と判定されます。
		ByteUnitString obj = new ByteUnitString("");
		assertTrue(obj.isIndexOutOfBounds(-1));
		assertTrue(obj.isIndexOutOfBounds(0));
		assertTrue(obj.isIndexOutOfBounds(1));
	}
	
	@Test
	public void インデックス範囲外の判定メソッド_半角() {
		// インデックス範囲外判定（isIndexOutOfBounds）が正しい真偽値が返ること。
		ByteUnitString obj = new ByteUnitString("abcde");
		// 半角5文字ならインデックス0～4は範囲外と判定されません。
		assertFalse(obj.isIndexOutOfBounds(0));
		assertFalse(obj.isIndexOutOfBounds(1));
		assertFalse(obj.isIndexOutOfBounds(2));
		assertFalse(obj.isIndexOutOfBounds(3));
		assertFalse(obj.isIndexOutOfBounds(4));
		// 半角5文字ならインデックス-1や5は範囲外と判定されます。
		assertTrue(obj.isIndexOutOfBounds(-1));
		assertTrue(obj.isIndexOutOfBounds(5));
	}
		
	@Test
	public void インデックス範囲外の判定メソッド_全角() {
		ByteUnitString obj = new ByteUnitString("あいう");
		// 全角3文字ならインデックス0～8は範囲外と判定されません。
		for (int index = 0; index < 9; index++) {
			assertFalse(obj.isIndexOutOfBounds(index));
		}
		// 全角3文字ならインデックス-1や9は範囲外と判定されます。
		assertTrue(obj.isIndexOutOfBounds(-1));
		assertTrue(obj.isIndexOutOfBounds(9));
	}
		
	@Test
	public void インデックス範囲外の判定メソッド_全角半角混在() {
		ByteUnitString obj = new ByteUnitString("あaいbcd");
		// 混在文字「あaいbcd」ならインデックス0～9は範囲外と判定されません。
		assertFalse(obj.isIndexOutOfBounds(0));
		assertFalse(obj.isIndexOutOfBounds(1));
		assertFalse(obj.isIndexOutOfBounds(2));
		assertFalse(obj.isIndexOutOfBounds(3));
		assertFalse(obj.isIndexOutOfBounds(4));
		assertFalse(obj.isIndexOutOfBounds(5));
		assertFalse(obj.isIndexOutOfBounds(6));
		assertFalse(obj.isIndexOutOfBounds(7));
		assertFalse(obj.isIndexOutOfBounds(8));
		assertFalse(obj.isIndexOutOfBounds(9));
		
		// 混在文字「あaいbcd」ならインデックス-1や10は範囲外と判定されます。
		assertTrue(obj.isIndexOutOfBounds(-1));
		assertTrue(obj.isIndexOutOfBounds(10));
	}
	
	@Test
	public void 空文字のインデックスのテスト2() {
		// 空文字ならインデックスがいくつでも範囲外と判定されます。
		ByteUnitString obj = new ByteUnitString("あいうえお");
		obj.substring(9, 15);
		// obj.setStartIndex(3);
		System.out.println(obj.toString());
	}
	
}
