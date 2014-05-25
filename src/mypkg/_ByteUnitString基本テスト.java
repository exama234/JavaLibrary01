package mypkg;

import static org.junit.Assert.fail;

import org.junit.Test;

public class _ByteUnitString基本テスト {
	
	@Test
	public void 空文字のコンストラクタが成功すること() {
		// 空文字のコンストラクタが成功すること。
		new ByteUnitString("");
	}
	
	@Test
	public void Null値のコンストラクタでNullPointerExceptionが発生すること() {
		// Null値のコンストラクタでNullPointerExceptionが発生すること。
		try {
			new ByteUnitString(null);
			fail();
		}catch (NullPointerException e) {
			// 例外発生が成功。
		}
	}
		
	@Test
	public void コンストラクタでキャラクタセット指定のテスト() {
		// キャラクタセット指定のコンストラクタが成功すること。
		new ByteUnitString("あいうえお", "Shift_JIS");
	}
	
}
