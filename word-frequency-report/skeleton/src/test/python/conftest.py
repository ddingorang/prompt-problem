import sys
from pathlib import Path

# 테스트가 스켈레톤 모듈을 임포트할 수 있도록 src/main/python을 경로에 올린다.
sys.path.insert(0, str(Path(__file__).resolve().parents[2] / "main" / "python"))
