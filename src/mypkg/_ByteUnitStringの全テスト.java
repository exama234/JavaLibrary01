package mypkg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{
		_ByteUnitString基本テスト.class,
		_ByteUnitString切り出しテスト.class,
		_ByteUnitStringインデックステスト.class,
		_ByteUnitString半端バイトのテスト.class,
		_ByteUnitStringキャラクタセット指定のテスト.class,
	}
)
public class _ByteUnitStringの全テスト {

}
