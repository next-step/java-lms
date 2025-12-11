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

-- Step 4: approval_policy 컬럼 추가
ALTER TABLE session ADD COLUMN approval_policy VARCHAR(10);

-- Step 5: 기존 데이터 마이그레이션 (기본값: AUTO)
UPDATE session SET approval_policy = 'AUTO';

-- Step 6: NOT NULL 제약조건 추가
ALTER TABLE session ALTER COLUMN approval_policy SET NOT NULL;