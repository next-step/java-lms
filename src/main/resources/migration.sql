-- Step 1: recruitment_status 컬럼 추가
ALTER TABLE session ADD COLUMN recruitment_status VARCHAR(20);

-- Step 2: 기존 데이터 마이그레이션
-- PREPARING -> progress_state: PREPARING, recruitment_status: NOT_RECRUITING
UPDATE session SET recruitment_status = 'NOT_RECRUITING' WHERE state = 'PREPARING';

-- RECRUITING -> progress_state: PREPARING, recruitment_status: RECRUITING
UPDATE session SET recruitment_status = 'RECRUITING' WHERE state = 'RECRUITING';

-- CLOSED -> progress_state: FINISHED, recruitment_status: NOT_RECRUITING
UPDATE session SET state = 'FINISHED', recruitment_status = 'NOT_RECRUITING' WHERE state = 'CLOSED';

-- Step 3: NOT NULL 제약조건 추가
ALTER TABLE session ALTER COLUMN recruitment_status SET NOT NULL;